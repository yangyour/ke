package cn.dblearn.dbblog.common.support.filter;

public class SiteThreadVariable {
    private static ThreadLocal<Long> siteVariable = new  ThreadLocal<Long>();

	public static Long getSite() {
		return siteVariable.get();
	}
	public static void setSite(Long site) {
		siteVariable.set(site);
	}
	public static void removeSite() {
		siteVariable.remove();
	}
}
