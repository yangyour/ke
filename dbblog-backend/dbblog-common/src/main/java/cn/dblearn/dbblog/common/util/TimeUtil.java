package cn.dblearn.dbblog.common.util;

/**
 *
 * @author developer_001
 *
 */
public class TimeUtil {
	public static final long MILLIS_PER_SECOND = 1000;
	public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
	public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

	/**
	 * 	毫秒转秒
	 * @param millis
	 * @return
	 */
	public static int millisToSecond(long millis){
		return (int)(millis/MILLIS_PER_SECOND);
	}
}
