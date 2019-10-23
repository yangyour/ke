package cn.dblearn.dbblog.common.enums;

/**
 * 缓存key
 * @author developer001
 */
public enum CacheKeyStore {
	SETTING("interim_setting","系统设置"),//系统设置
	SHIRO_CACHE("interim_shiro_cache_","shiro缓存"),//shiro缓存
	MALL_CATALOGS_NAV("interim_mall_catalogs_nav","商城品目导航"),//商城品目导航
	DICT_DATA("interim_dict_data","字典数据"),//字典数据
	EMALL_API_STANDARD_TOKENS("interim_stamdardemall_api_tokens","标准电商tokens"),//标准电商tokens
	EMALL_API_JD_TOKEN("interim_jdemall_api_token","京东电商token"),//京东电商token
	MALL_NAVIGATION("interim_mall_navigation","商城导航"),//商城导航
	PORTAL_NAVIGATION("interim_portal_navigation","门户导航"),//门户导航
	MALL_BANNERS("interim_mall_banners","商城banners"),//商城banners
	PORTAL_BANNERS("interim_portal_banners","门户banners"),//门户banners
	INDEXPAGE_ARTICLE_RIGHT("interim_indexpage_article_right","网站右侧内容显示"),//网站右侧内容显示
	INDEXPAGE_EMALLS("interim_indexpage_emalls","首页电商列表"),//首页电商列表
	MALL_INDEXPAGE_FLOORS("interim_mall_indexpage_floors","商城首页楼层"),//商城首页楼层
	HOMEPAGE_WEBSITE_LINKS("interim_homepage_website_links","主页网站链接");//主页网站链接
	CacheKeyStore(String v,String name){
		this.v = v;
		this.name = name;
	}
	String v;
	String name;//缓存名称
	public String getValue(){
		return v;
	}

	public String getName(){
		return name;
	}
}
