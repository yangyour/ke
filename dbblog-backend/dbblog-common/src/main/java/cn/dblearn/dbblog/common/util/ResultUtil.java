package cn.dblearn.dbblog.common.util;

import java.util.HashMap;
import java.util.Map;

import cn.dblearn.dbblog.common.warpper.HttpResponseCode;

/**
 *
 * @Title: ResultUtil
 * @Description: 增删改-返回工具类
 * @author: Zhou Wei
 * @date: 2018年7月24日 下午5:15:36
 */
public class ResultUtil {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String MESSAGE = "message";

	public static final String STATUS = "status";

	/**
	 *
		 * @Title: result
		 * @author: Zhou Wei
		 * @date: 2018年7月24日 下午5:16:16
		 * @Description: 传入的参数大于0返回success，小于0返回error
		 * @return: Map<String, Object>
	 */
	public static Map<String, Object> result(int compare,int standard) {
		Map<String, Object> map = new HashMap<String,Object>();
		if (compare>standard) {
			map.put(MESSAGE, SUCCESS);
		}else {
			map.put(MESSAGE, ERROR);
		}
		return map;
	}

	/**
	 * <p>Title: resultMessage</p>
	 * <p>Description: </p>
	 * @author Yml
	 * @param result 操作数据库所影响行数返回结果
	 * @param message
	 * @return
	 */
	public static Map<String, Object> resultMessage(int result, String message) {
		Map<String, Object> map = new HashMap<String,Object>();
		if (result > 0) {
			map.put(STATUS, SUCCESS);
			map.put(MESSAGE, message);
		}else {
			map.put(STATUS, ERROR);
			map.put(MESSAGE, message);
		}
		return map;
	}

	/**
	 * <p>Title: resultMessage</p>
	 * <p>Description: </p>
	 * @author Yml
	 * @param status:success/error
	 * @param message
	 * @return
	 */
	public static Map<String, Object> resultMessage(String status, String message) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(STATUS, status);
		map.put(MESSAGE, message);
		return map;
	}

	/**
	 * add by YF 2018-11-14 17:36
	 * @param httpResponseCode
	 * @param message
	 * @return
	 */
	public static Map<String, Object> resultMessage(HttpResponseCode httpResponseCode,String message) {
		return resultMessage(httpResponseCode, message,null);
	}

	/**
	 * add by YF 2018-11-15 20:16
	 * @param httpResponseCode
	 * @param message
	 * @return
	 */
	public static Map<String, Object> resultMessage(HttpResponseCode httpResponseCode,String message,Map<String,Object> data) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(STATUS, httpResponseCode==HttpResponseCode.OK?SUCCESS:httpResponseCode.value().toString());
		map.put(MESSAGE, message);
		if(data!=null){
			map.putAll(data);
		}
		return map;
	}


	/**
	 * <p>Title: success</p>
	 * <p>Description: 返回成功消息</p>
	 * @author Yml
	 * @param message
	 * @return
	 */
	public static Map<String, Object> success(String message) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(STATUS, SUCCESS);
		map.put(MESSAGE, message);
		return map;
	}

	/**
	 * <p>Title: error</p>
	 * <p>Description: 返回错误消息</p>
	 * @author Yml
	 * @param message
	 * @return
	 */
	public static Map<String, Object> error(String message) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(STATUS, ERROR);
		map.put(MESSAGE, message);
		return map;
	}


}
