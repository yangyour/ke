package cn.dblearn.dbblog.common;

import java.math.BigDecimal;

/**

 * <p>Title: Constant</p>

 * <p>Description: 常量类</p>

 * @author Yml

 * @date 2018年10月29日

 */
public class Constant {

    /*
     * 编码格式
     */
    public static final String CHARSET_ENCODING = "utf-8";

    /**
     * 日期类转换匹配格式
     */
    public static final String[] DATE_PATTERNS = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 商城网站Url
     */
    public static String mallSiteUrl = "";

    /**
     * 商城网站ctx
     */
    public static String mallSiteCtx = "";

    /*
     * 图片站点
     */
    public static String imgSiteUrl = "";

    /*
     * 上传基础路径
     */
    public static String UPLOAD_BASE_PATH = "";

    /*
     *图片上传路径
     */
    public static final String IMAGE_UPLOAD_PATH = "/upload/image/${.now?string('yyyyMM')}/${.now?string('dd')}/";

    /*
     * 文件上传路径
     */
    public static final String FILE_UPLOAD_PATH = "/upload/file/${.now?string('yyyyMM')}/${.now?string('dd')}/";

    /**
     * 权限分隔符
     */
    public static final String PERM_SEPARATOR = ",";

    /**
     * session key 定义
     */
    public static final class SessionKeyStore {
        public static final String LOGIN_USER = "loginUser";
        public static final String USER_ROLE = "userRole";
        public static final String USER_MENU = "userMenu";
    }

    /**
     * 购物车最大商品数
     */
    public static final Integer MAX_PRODUCT_CART_COUNT = 9999;

    /**
     * es 默认集群名称
     */
    public static String ES_DEFAULT_CLUSTERNAME = "elasticsearch";

    /**
     * 默认参数组名称
     */
    public static final String DEFAULT_PARAMETER_GROUP_NAME = "基本参数";

    /**
     * 相似商品价格系数
     */
    public static final BigDecimal SIMILAR_PRICE_RATE = new BigDecimal(0.1);

    /**
     * 百分比系数
     */
    public static final BigDecimal PERCENT_RATIO = new BigDecimal(100);

    /**
     * 默认邮编
     */
    public static final String DEFALUT_ZIP_CODE = "163000"; //默认大庆

}

