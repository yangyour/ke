package cn.dblearn.dbblog.common.context;

import cn.dblearn.dbblog.common.context.log.CudLogManager;

/**
 * 上下文线程变量
 * @author developer001
 *
 */
public class ContextThreadVariable {

	/**
	 * 日志管理线程变量
	 */
	private static ThreadLocal<CudLogManager> logManagerVariable = new ThreadLocal<CudLogManager>();


	public static CudLogManager getCudLogManager() {
		return logManagerVariable.get();
	}

	public static void setCudLogManager(CudLogManager logManager) {
		logManagerVariable.set(logManager);
	}

	public static void remoteCudLogManager(){
		logManagerVariable.remove();
	}
}
