package cn.dblearn.dbblog.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import cn.dblearn.dbblog.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import com.google.common.collect.Maps;

/**
 * request工具类
 *
 * @author developer001
 */
public class RequestUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(RequestUtil.class);

	/**
	 * HTTP GET请求
	 */
	public static final String GET = "GET";

	private RequestUtil(){}

	/**
     * 获取所有请求的值
     */
    @SuppressWarnings("rawtypes")
	public static Map<String, String> getRequestParameters(HttpServletRequest request) {
        HashMap<String, String> values = Maps.newHashMap();
        Enumeration enums = request.getParameterNames();
        while ( enums.hasMoreElements()){
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);
            values.put(paramName, paramValue);
        }
        return values;
    }


	public static Map<String, Object> getQueryParams(HttpServletRequest request) {
		Map<String, String[]> map = Maps.newHashMap();
		if (request.getMethod().equalsIgnoreCase(GET)) {
			String s = request.getQueryString();
			if (StringUtils.isBlank(s)) {
				return new HashMap<String, Object>();
			}
			try {
				s = URLDecoder.decode(s, Constant.CHARSET_ENCODING);
			} catch (UnsupportedEncodingException e) {
				logger.error("encoding " + Constant.CHARSET_ENCODING + " not support?", e);
			}
		} else {
			map = request.getParameterMap();
		}

		Map<String, Object> params = new HashMap<String, Object>(map.size());
		int len;
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			len = entry.getValue().length;
			if (len == 1) {
				params.put(entry.getKey(), entry.getValue()[0]);
			} else if (len > 1) {
				params.put(entry.getKey(), entry.getValue());
			}
		}
		return params;
	}

    public static Map<String, String> parseQueryString(String s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		Map<String, String> ht = new HashMap<String, String>();
		StringTokenizer st = new StringTokenizer(s, "&");
		while (st.hasMoreTokens()) {
			String pair = (String) st.nextToken();
			int pos = pair.indexOf('=');
			if (pos == -1) {
				continue;
			}
			String key = pair.substring(0, pos);
			String val = pair.substring(pos + 1, pair.length());
			ht.put(key, val);
		}
		return ht;
	}


    /**
	 * 获得当的访问路径
	 *
	 * @param request
	 * @return
	 */
	public static String getLocation(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		StringBuffer buff = request.getRequestURL();
		String uri = request.getRequestURI();
		String origUri = helper.getOriginatingRequestUri(request);
		buff.replace(buff.length() - uri.length(), buff.length(), origUri);
		String queryString = helper.getOriginatingQueryString(request);
		if (queryString != null) {
			buff.append("?").append(queryString);
		}
		return buff.toString();
	}

	/**
     * 判断是否为Ajav请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request){
    	return  (request.getHeader("X-Requested-With") != null
        	    && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    /**
     * 判断是否是XML异步请求
     * @param request
     * @return
     */
    public static boolean isXmlAjaxRequest(HttpServletRequest request){
		return  request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1;
	}

	/**
    *
    * 获取请求客户端ip
    *
    * @param request
    *
    * @return ip地址
    *
    */
   public static  String getRemoteAddress(HttpServletRequest request) {

       String ip = request.getHeader("x-forwarded-for");
       if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
           ip = request.getHeader("Proxy-Client-IP");
       }
       if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
           ip = request.getRemoteAddr();
       }
       return ip;
   }

}
