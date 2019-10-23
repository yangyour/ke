package cn.dblearn.dbblog.common.context.log;

/**
 * 增改删日志管理
 * @author developer001
 *
 */
public interface CudLogManager {

	/**
	 * 执行日志
	 */
	void excuteLog(Object before, Object after, CudType type);

	/**
	 * 获取日志对象
	 * @param log
	 */
	ContextOperateLog getContextOperateLog();
}
