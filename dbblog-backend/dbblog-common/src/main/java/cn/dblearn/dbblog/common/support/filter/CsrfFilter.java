package cn.dblearn.dbblog.common.support.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cn.dblearn.dbblog.common.util.JsonUtil;
import cn.dblearn.dbblog.common.util.PropertiesUtil;
import cn.dblearn.dbblog.common.util.RequestUtil;
import cn.dblearn.dbblog.common.util.ResultUtil;
import cn.dblearn.dbblog.common.util.StringUtilEx;
import cn.dblearn.dbblog.common.warpper.HttpResponseCode;

import com.google.common.collect.Sets;

/**
 * 跨站请求伪造拦截
 * @author developer001
 */
public class CsrfFilter implements Filter {
    private Logger logger = LogManager.getLogger();

    // 白名单
    private Set<String> whiteUrls = Sets.newHashSet();

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("init CsrfFilter..");
        // 读取文件
        String[] csrfWhiteUrls = PropertiesUtil.getString("security.csrfWhiteUrls", "").split(",");
        for(String url:csrfWhiteUrls){
        	whiteUrls.add(url);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest)request;

            String referurl = req.getHeader("Referer");
            if (isWhiteRequest(referurl)) {
                chain.doFilter(request, response);
            } else {
            	// 获取请求url地址
                String url = req.getRequestURL().toString();
                logger.warn("跨站请求---->>>{} || {} || {} ", url, referurl, RequestUtil.getLocation(req));
                response.setContentType("application/json;charset=UTF-8");
                Map<String, Object> msg = ResultUtil.resultMessage(HttpResponseCode.NOT_ACCEPTABLE,"错误的请求头信息",null);
                response.getOutputStream().write(JsonUtil.toJson(msg).getBytes());
                return;
            }
        } catch (Exception e) {
            logger.error("doFilter", e);
        }
    }

    /**
     * 是否白名单请求
     * @param url
     * @return
     */
    private boolean isWhiteRequest(String url){
    	if(StringUtilEx.isEmpty(url)||whiteUrls.isEmpty()){
    		return true;
    	}else{
            url = url.toLowerCase();
            for (String urlTemp : whiteUrls) {
                if (url.indexOf(urlTemp.toLowerCase()) > -1) {
                    return true;
                }
            }
        }
	   return false;
    }

    @Override
    public void destroy() {
        logger.info("destroy CsrfFilter.");
    }
}
