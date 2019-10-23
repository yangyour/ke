package cn.dblearn.dbblog.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.client.ClientProtocolException;
import cn.dblearn.dbblog.common.exception.HttpRequestException;

import com.beust.jcommander.internal.Maps;

/**
 * 短信工具类
 *
 */
public class SMSUtil {

	private static final Logger logger = LogManager.getLogger();
	//测试地址     https://api.ums86.com:9600/sms/Api/Send.do
	//测试帐户模板1：你有一项编号为{xxxxxxxxx}的事务需要处理{x}
	//发送示例1：    你有一项编号为123456789的事务需要处理。
	//测试帐户模板2： 您的验证码为{xxxxxxx}
	//发送示例2:      您的验证码为：123456
	//测试账号：企业编号 ：SpCode 用户名称：bj_yggc 用户密码 ：yggc514029
	//正式账号  企业编号 ：253944 用户名称：admin0 用户密码 ：54j2c3iO

	/**
	 *
	 * @param mobile 手机号，多个用逗号隔开
	 * @param content 发送内容
	 * @return map   result:结果标识(0 成功 ,其它失败);  msg:结果描述
	 */
	public static Map<String, Object> sendMes(String mobile,String content) {
		Map<String, String> map = Maps.newHashMap();
		//企业编号
		map.put("SpCode", "253944");
		//用户名称
		map.put("LoginName", "admin0");
		//用户密码
		map.put("Password", "54j2c3iO");
		//短信内容
		map.put("MessageContent", content);
		//手机号
		map.put("UserNumber", mobile);
		//20位流水号
		map.put("SerialNumber", getSerialNum());
		//预约发送时间，格式:yyyyMMddHHmmss,如‘20090901010101’，立即发送请填空
		map.put("ScheduleTime", "");
		//接入号扩展号，默认不填
		map.put("ExtendAccessNum", "");
		//提交时检测方式
		//1 --- 提交号码中有效的号码仍正常发出短信，无效的号码在返回参数faillist中列出
		//不为1 或该参数不存在 --- 提交号码中只要有无效的号码，那么所有的号码都不发出短信，无效的号码在返回参数faillist中列出
		map.put("f", "1");
		return sms(map);
	}


	private static Map<String, Object> sms(Map<String, String> map) {
		String doPost = null;
		try {
			doPost = HttpClient.doPost("https://api.ums86.com:9600/sms/Api/Send.do",map,null,"GBK");
//			System.out.println("=========短信发送返回值："+doPost);
			logger.info("=========短信发送内容："+map);
			logger.info("=========短信发送返回值："+doPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (HttpRequestException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return result(doPost);
//		return result(doPost);
	}


	public static void main(String[] args) throws ClientProtocolException, IOException, RuntimeException {
		Map<String, Object> result = sendMes("17610776580","验证码为:956201。您正在使用短信验证码注册功能，2分钟内有效。转发可能导致账号被盗，请勿泄露给他人。");

//		Map<String, Object> result = sendMes("18253150682","大庆市体育局计算机商城竞价开始，竞价截止时间为2019年06月23日15:00:00，如有意愿参与本项目，请登陆大庆市政府采购网上商城进行报价。");
		System.out.println(result);
	}

	/**
	 * 解析返回值
	 * @param doPost  例result=0&description=发送短信成功&taskid=214894830147&faillist=&task_id=214894830147
	 * @return
	 */
	private static Map<String, Object> result(String doPost){
		Map<String, Object> map=new HashMap<String, Object>();
		String[] result = doPost.split("&");
		String resultCode = result[0];
		String resultMsg = result[1];
		String[] codeStr = resultCode.split("=");
		String[] msgStr = resultMsg.split("=");
		map.put("result", codeStr[1]);
		map.put("msg", msgStr[1]);
		return map;
	}

	//获得20位序列号  17位时间+001
	public static String getSerialNum() {
		String serialNum = null;
		Date date = new Date();
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String timeStr = dft.format(date);
		timeStr += "001";
		return serialNum;
	}

}
