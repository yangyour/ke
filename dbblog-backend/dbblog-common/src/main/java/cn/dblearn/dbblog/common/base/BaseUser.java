package cn.dblearn.dbblog.common.base;

/**
 * 基础用户
 * @author developer001
 */
public abstract class BaseUser extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 登录名
	* @return String
	*/
	public abstract String getLoginName();

	/**
	 * 真实姓名
	 * @return
	 */
	public abstract String getRealName();

	/**
	* 用户类型：1：采购人，2：供应商
	* @return Integer
	*/
	public abstract Integer getType();

	/**
	* 关联id
	* @return Long
	*/
	public abstract Long getTypeId();
}
