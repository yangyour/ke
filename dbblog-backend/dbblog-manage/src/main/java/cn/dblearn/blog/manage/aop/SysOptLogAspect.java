package cn.dblearn.blog.manage.aop;

import cn.dblearn.blog.common.enums.SysOperateLogType;
import cn.dblearn.blog.entity.logs.SysOperateLogs;
import cn.dblearn.blog.manage.logs.service.SysOperateLogsService;
import cn.dblearn.blog.manage.utils.SysOptManager;
import cn.dblearn.dbblog.common.annotation.SysOptLog;
import cn.dblearn.dbblog.common.context.ContextThreadVariable;
import cn.dblearn.dbblog.common.core.UserUtil;
import cn.dblearn.dbblog.common.util.RequestUtil;
import cn.dblearn.dbblog.common.warpper.Principal;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 *
 * 操作日志切面
 * @author developer001
 *
 */
@Aspect
@Component
public class SysOptLogAspect {

	@Autowired
    @Lazy
	private SysOperateLogsService sysOperateLogsService;

	@Autowired
	private SysOptManager logManager;

	@Pointcut("@annotation(cn.dblearn.dbblog.common.annotation.SysOptLog)" )
	public void executeOptLog(){}

	@Around("executeOptLog()")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Method method = currentMethod(joinPoint, methodName);
        SysOptLog annotation = method.getAnnotation(SysOptLog.class);
        if (null!=annotation) {
        	//获取区域站点id
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			SysOperateLogs log=new SysOperateLogs();
			Date date = new Date();
			log.setCreatedAt(date);
			log.setUpdatedAt(date);
			log.setLogType(SysOperateLogType.opt);
			log.setIp(RequestUtil.getRemoteAddress(request));
			log.setDescription(annotation.opt());;
			processLogs(log, request);
			sysOperateLogsService.save(log);
			logManager.setContextOperateLog(log);
			ContextThreadVariable.setCudLogManager(logManager);
		}
        return joinPoint.proceed();
    }

	@After("executeOptLog()")
    public void after() {
		ContextThreadVariable.remoteCudLogManager();
    }

	/**
     * 获取当前方法
     * @param joinPoint
     * @param methodName
     * @return
     */
    public Method currentMethod(ProceedingJoinPoint joinPoint, String methodName){
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }

    private void processLogs(SysOperateLogs log, HttpServletRequest request){
    	UrlPathHelper helper = new UrlPathHelper();
		String contextpath = helper.getContextPath(request);
		String requestUri = helper.getOriginatingRequestUri(request);
		String url = StringUtils.isEmpty(contextpath)?requestUri:StringUtils.substringAfter(requestUri, contextpath);
		String urlPattern = StringUtils.substringBeforeLast(url, "/");
		if(StringUtils.isNotEmpty(urlPattern)){
			url = urlPattern;
		}

		Principal p = UserUtil.currentLoginPrincipal();
		if(p!=null){
			log.setOperator(StringUtils.isNotEmpty(p.getRealname())?p.getRealname():p.getUsername());
			log.setOperatorDep(p.getDeptName());
			log.setLoginName(p.getUsername());
			log.setUserId(p.getId());
		}
    }

    /**
     * 描述
     * @param principal
     * @param opt
     * @param request
     * @return
     */
    /*
    private String buildDesc(String opt,HttpServletRequest request){
    	String dateStr = DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
    	if(StringUtilEx.isEmpty(principal.getRealname())){
    		return "IP:"+RequestUtil.getRemoteAddress(request)+"于"+dateStr+opt;
    	}
    	return principal.getRealname()+"于"+dateStr+opt;
    }
    */

}
