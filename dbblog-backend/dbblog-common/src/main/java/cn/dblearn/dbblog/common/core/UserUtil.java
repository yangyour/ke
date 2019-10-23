package cn.dblearn.dbblog.common.core;

import cn.dblearn.dbblog.common.Constant;
import cn.dblearn.dbblog.common.base.BaseUser;
import cn.dblearn.dbblog.common.context.ApplicationContextHolder;
import cn.dblearn.dbblog.common.warpper.Principal;
import cn.dblearn.dbblog.common.warpper.TreeNode;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


import java.util.Iterator;
import java.util.List;

/**
 * 用户工具类
 * @author developer001
 *
 */
public class UserUtil {

	private static final Logger logger = LogManager.getLogger();

	private UserUtil(){}

	/**
	 * 是否已登录
	 * @return
	 */
	public static boolean isLogin(){
		 return getSubject() != null && getSubject().isAuthenticated();
	}

	/**
	 * 创建Principal
	 * @param user
	 * @return
	 */
	public static Principal newInstancePrincipal(BaseUser user){
		Principal p = new Principal(user.getId(),user.getLoginName(),user.getRealName());
		p.setType(user.getType());
		if(user.getType()!=null&&user.getType()==2){
			p.setSupplierId(user.getTypeId());
		}

		return p;
	}

	/**
	 * 当前登录用户 - session获取
	 * @return
	 */
	 public static Principal currentLoginPrincipal(){
         if (getSubject() != null) {
       	  Principal p = (Principal)getSubject().getSession().getAttribute(Constant.SessionKeyStore.LOGIN_USER);
       	  return p;
         }
         return null;
	   }

	 	/**
		 * 当前登录用户 - 数据库查询获取
		 * @return
		 */
		public static BaseUser currentLoginUser(){
			Principal principal= currentLoginPrincipal();
			if(principal!=null){
				IUserService usersService = ApplicationContextHolder.getBean(IUserService.class);
				BaseUser user = usersService.findById(principal.getId());
				return user;
			}
			return null;
		}

	 	/**
	     * 当前用户权限
	     * @param permId
	     * @return
	     */
	    @SuppressWarnings("unchecked")
		public static List<TreeNode> userTreePerms(Long permId){
	    	List<TreeNode> userPerms = Lists.newArrayList();
	    	if(getSubject()!=null){
	    		userPerms = (List<TreeNode>)getSubject().getSession().getAttribute(Constant.SessionKeyStore.USER_MENU);
	    		if(userPerms==null||permId==null){
	    			return userPerms;
	    		}

	    		//表示获取顶级权限
	    		if(permId<1){
	    			return userPerms;
	    		}

	    		//获取顶级权限下的子权限
	    		for (Iterator<TreeNode> iterator = userPerms.iterator(); iterator.hasNext();) {
	    			TreeNode permission = (TreeNode) iterator.next();
					if(permission.getId()!=null&&permission.getId().equals(permId)){
						userPerms = permission.getChildren();
						break;
					}
				}
	    	}
	    	return userPerms;
	    }

	public static Subject getSubject() {
		try{
			return SecurityUtils.getSubject();
		}catch (Exception e) {
			//logger.error(e.getLocalizedMessage());
		}
		return null;
    }
}
