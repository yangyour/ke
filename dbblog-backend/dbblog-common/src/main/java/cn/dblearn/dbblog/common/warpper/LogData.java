package cn.dblearn.dbblog.common.warpper;

import java.io.Serializable;

/**
 * 日志数据封装
 * @author developer001
 *
 */
public class LogData implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Object before; //之前数据
	private Object current;//当前数据

	public LogData(){}

	public LogData(Object before,Object current){
		this.before = before;
		this.current = current;
	}

	public Object getBefore() {
		return before;
	}

	public void setBefore(Object before) {
		this.before = before;
	}

	public Object getCurrent() {
		return current;
	}

	public void setCurrent(Object current) {
		this.current = current;
	}

}
