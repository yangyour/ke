package cn.dblearn.dbblog.common.context;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import cn.dblearn.dbblog.common.core.ExceptionLogBean;
import cn.dblearn.dbblog.common.core.ISysExceptionLogsService;
import cn.dblearn.dbblog.common.core.UserUtil;
import cn.dblearn.dbblog.common.exception.SystemException;
import cn.dblearn.dbblog.common.util.JsonUtil;
import cn.dblearn.dbblog.common.util.RequestSiteUtil;
import cn.dblearn.dbblog.common.util.RequestUtil;
import cn.dblearn.dbblog.common.util.ResultUtil;
import cn.dblearn.dbblog.common.warpper.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;


/**
 * 拓展异常解析
 * @author developer001
 *
 */
@Component
public class ExtSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver{
	private static final Logger logger = LoggerFactory
			.getLogger(ExtSimpleMappingExceptionResolver.class);
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String errorMsg = "系统异常";

		if(ex instanceof AuthorizationException){
			errorMsg = "没有权限";
		}

		if(ex instanceof SystemException){
			errorMsg = ex.getMessage();
		}else if(ex instanceof UnknownAccountException){
			errorMsg = "账号不存在";
		}else if(ex instanceof IncorrectCredentialsException){
			errorMsg = "账号或密码错误";
		}
		logger.error(ex.getLocalizedMessage());

		response.setContentType("text/json; charset=UTF-8");

		Map<String, Object> result = ResultUtil.resultMessage(0, errorMsg);
		try {
			JsonUtil.writeValue(response.getWriter(), result);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		exceptionSaveLog(request, handler, ex);
		return null;
	}

	/**
	 * 异常日志保存
	 * @param request
	 * @param handler
	 * @param ex
	 */
	private void exceptionSaveLog(HttpServletRequest request, Object handler, Exception ex){
		String controller = null;
		String controllerMethod = null;
		if(handler!=null && handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			controller = handlerMethod.getBeanType().getName();
			controllerMethod = handlerMethod.getMethod().getName();
		}

		UrlPathHelper helper = new UrlPathHelper();
		String contextpath = helper.getContextPath(request);
		String requestUri = helper.getOriginatingRequestUri(request);
		requestUri = StringUtils.isEmpty(contextpath)?requestUri:StringUtils.substringAfter(requestUri, contextpath);
		StringBuffer title = new StringBuffer();
		title.append("访问").append(requestUri).append("发生").append(ex.getClass().getSimpleName()).append("异常");

		String name = ex.getClass().getName();
		String message = ex.getMessage();
		if(StringUtils.isNotBlank(message)&&message.length()>=255){
			message=StringUtils.substring(message, 0, 255-3)+"...";
		}

		Map<String, Object> paramsMap = RequestUtil.getQueryParams(request);
		String params = (paramsMap!=null&&paramsMap.size()>0)?JsonUtil.toJson(RequestUtil.getQueryParams(request)):null;

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String stackTrace = sw.toString();

		String httpMethod = request.getMethod();

		String ip = RequestUtil.getRemoteAddress(request);
		String localIp = null;
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
		    localIp=addr.getHostAddress().toString(); //获取本机ip
		} catch (UnknownHostException e) {
		}

		String httpUrl = StringUtils.isEmpty(contextpath)?requestUri:StringUtils.substringAfter(requestUri, contextpath);
		Long siteId = RequestSiteUtil.getSiteId(request);
		ExceptionLogBean exLogs = new ExceptionLogBean();
		exLogs.setName(name);
		exLogs.setMessage(message);
		exLogs.setParams(params);
		exLogs.setStackTrace(stackTrace);
		exLogs.setHttpMethod(httpMethod);
		exLogs.setIp(ip);
		exLogs.setLocalIp(localIp);
		exLogs.setHttpUrl(httpUrl);
		exLogs.setController(controller);
		exLogs.setControllerMethod(controllerMethod);
		exLogs.setTitle(title.toString());
		exLogs.setSiteId(siteId);

		Principal principal = UserUtil.currentLoginPrincipal();
		if(principal!=null){
			exLogs.setUserId(principal.getId());
			exLogs.setLoginName(principal.getUsername());
		}

		ISysExceptionLogsService service = ApplicationContextHolder.getBean(ISysExceptionLogsService.class);
		service.saveLog(exLogs);
	}

}
