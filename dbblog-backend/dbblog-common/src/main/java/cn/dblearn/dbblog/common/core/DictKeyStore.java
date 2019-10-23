package cn.dblearn.dbblog.common.core;

/**
 * dict key 定义
 */
public final class DictKeyStore {
	//系统设置属性前缀
    public static final String SETTING_ATTRIBUTE_PREFIX = "setting.attribute.";

	//电商job启用前缀
    public static final String EMALL_JOB_ENABLE_PREFIX = "emallJob.enable.";

    //电商账期支付提示前缀
    public static final String EMALL_PERIODPAY_MSG_PREFIX = "emallJob.periodpay.msg.";

    //es是否启用key
    public static final String ES_ENABLE_KEY = "es.enable";

    /**
     * 导航第一个文章栏目ID标识(如通知公告)，多个英文逗号隔开
     */
    public static final String NAVIGATION_ARTICLE_FIRSTTYPEIDS = "navigation.article.firsttypeIds";

    /**
     * 网站右侧第一个内容显示栏目ID标识，多个英文逗号隔开
     */
    public static final String INDEXPAGE_ARTICLE_RIGHT_FIRSTTYPEIDS = "indexPage.articleRight.firstTypeIds";

    /**
     * 网站右侧第第二个内容显示栏目ID标识，多个英文逗号隔开
     */
    public static final String INDEXPAGE_ARTICLE_RIGHT_SECONDTYPEIDS = "indexPage.articleRight.secondTypeIds";

    /**
     * 网站右侧第一个内容显示数量标识
     */
    public static final String INDEXPAGE_ARTICLE_RIGHT_FIRSTCOUNT = "indexPage.articleRight.firstCount";

    /**
     * 网站右侧第第二个内容显示数量标识
     */
    public static final String INDEXPAGE_ARTICLE_RIGHT_SECONDCOUNT = "indexPage.articleRight.secondCount";

    /**
     * 商城地区省ID
     */
    public static final String MALL_AREA_PROVINCEID = "mall.area.provinceid";

    /**
     * 商城默认使用地区ID
     */
    public static final String MALL_AREA_DEFAULTID = "mall.area.defaultid";

    /**
     * 直购项目公告栏目ID标识
     */
    public static final String MALL_ARTICLETYPE_NOTICE_ID = "articleType.mallNotice.id";

    /**
     * 竞价项目公告栏目ID标识
     */
    public static final String BID_ARTICLETYPE_NOTICE_ID = "articleType.bidNotice.id";

    /**
     * 定点项目公告栏目ID标识
     */
    public static final String FIX_ARTICLETYPE_NOTICE_ID = "articleType.fixNotice.id";


    /**
     * 监听事件是否启用前缀
     */
    public static final String LISTENEVENT_ISEABLE_PREFIX = "listenevent.isEnable.";

    /**
     * 枚举字典前缀
     */
    public static final String ENUMDICT_PREFIX = "enumDict.";

    /**
     * 是否开启电商接口
     */
    public static final String EMALLAPI_ENABLE = "emallApi.enable";

    /**
     * 查询多少天内的订单，value必须为负数
     */
    public static final String ORDER_QUERY_DAY_NUM = "orderQuery.dayNum";

    /**
     * 品目导航限额集采第一个金额
     */
    public static final String CATALOGNAV_NONCOLLECTS_ATH = "catalognav.noncollects_ath";

    /**
     * 品目导航限额集采第二个金额
     */
    public static final String CATALOGNAV_NONCOLLECTS_BTH = "catalognav_noncollects_bth";

}
