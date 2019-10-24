package cn.dblearn.dbblog.common.util;


import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


/**
 * 拓展工具类 - 字符串
 * @author developer001
 *
 */
public class StringUtilEx extends StringUtils {

	/**
	 * 驼峰式字符串转下划线
	 * @param param
	 * @return
	 */
	public static String humpToUnderline(String param){
		if (param==null||"".equals(param.trim())){
	           return "";
	       }
		  param = uncapitalize(param);//首字母小写
	       int len=param.length();
	       StringBuilder sb=new StringBuilder(len);
	       for (int i = 0; i < len; i++) {
	           char c=param.charAt(i);
	           if (Character.isUpperCase(c)){
	               sb.append("_");
	               sb.append(Character.toLowerCase(c));
	           }else{
	               sb.append(c);
	           }
	       }
	       return sb.toString();
	}

	/**
	 * 下划线字符串转驼峰式
	 * @param param
	 * @return
	 */
	public static String underlineToHump(String param){
	       if (param==null||"".equals(param.trim())){
	           return "";
	       }
	       int len=param.length();
	       StringBuilder sb=new StringBuilder(len);
	       for (int i = 0; i < len; i++) {
	           char c=param.charAt(i);
	           if (c=='_'){
	              if (++i<len){
	                  sb.append(Character.toUpperCase(param.charAt(i)));
	              }
	           }else{
	               sb.append(c);
	           }
	       }
	       return sb.toString();
	   }

	/**
	 *  文本剪切
	 * @param text 字符串文本
	 * @param len 剪切长度
	 * @param append 追加字符
	 * @return
	 */
	public static String textCut(String text, int len, String append) {
		if (text == null) {
			return "";
		}
		append = append==null?"":append;
		int reInt = 0;
		String reText = "";
		char[] tempChar = text.toCharArray();
		for (int i = 0; (i < tempChar.length && len > reInt); i ++) {
			byte[] bytes = String.valueOf(tempChar[i]).getBytes();
			reInt += bytes.length;
			reText += tempChar[i];
		}
		if (reText != null && (len == reInt || (len == reInt - 1))) {
			reText += append;
		}
		return reText;
	}

	/**
	 * 字节转字符串
	 * @param bytes
	 * @return
	 */
	public static String bytesToString(byte[] bytes){
		return bytesToString(bytes,Charset.defaultCharset().toString());
	}

	/**
	 *  字节转字符串
	 * @param bytes
	 * @param charset
	 * @return
	 */
	public static String bytesToString(byte[] bytes,String charset)
	{
		try {
			return new String(bytes,charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] stringToBytes(String str){
		return stringToBytes(str,Charset.defaultCharset().toString());
	}
	public static byte[] stringToBytes(String str,String charset){
		try {
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 提取中文/字母/数字的文本
	 * @param value
	 * @return
	 */
	public static String extractText(String value){
		if(isNotBlank(value)){
			return value.replaceAll("[^a-z^A-Z^0-9^\u4e00-\u9fa5]", "");
		}
		return null;
	}

	/**
	 * 提取中文/字母/数字的文本  转大写
	 * @param value
	 * @return
	 */
	public static String extractTextUpperCase(String value){
		String text = extractText(value);
		if(isNotEmpty(text)){
			return extractText(value).toUpperCase();
		}
		return null;
	}
}
