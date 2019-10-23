package cn.dblearn.dbblog.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class RequestSiteUtil {

    public static Long getSiteId(HttpServletRequest request) {
        return (Long) request.getAttribute("siteId");
    }

    public static String domain(HttpServletRequest request) {
        String domainUrl = "";
        String header = request.getHeader("Referer");
        Pattern p = Pattern.compile(
                "[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(header.split("\\?")[0]);
        while (matcher.find()) {
            domainUrl = matcher.group();
        }
        return domainUrl;
    }

}
