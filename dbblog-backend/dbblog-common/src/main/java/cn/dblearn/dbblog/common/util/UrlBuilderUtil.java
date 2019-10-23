//package cn.dblearn.dbblog.common.util;
//
//import cn.dblearn.dbblog.common.Constant;
//
///**
// * url生成工具
// * @author developer001
// *
// */
//public class UrlBuilderUtil {
//
//
//	public static String getMallProductUrl(Long catalogId,Long productId){
//		return getBase()+"/html/product.html?c_"+catalogId+"|p_"+productId;
//	}
//
//
//	public static String getMallCatalogUrl(Long catalogId,boolean isParent){
//		return getBase()+"/html/goodList.html?c_"+catalogId+"|t_"+(isParent?1:0);
//	}
//
//	/**
//	 * 得到文章Url
//	 * @param catalogId
//	 * @param isParent
//	 * @return
//	 */
//	public static String getArticleUrl(long id){
//		return getBase()+"/html/announcement.html?aid_"+id;
//	}
//
//	/**
//	 * 得到文章栏目Url
//	 * @return
//	 */
//	public static String getArticleTypeUrl(long[] ids){
//		return getBase()+"/html/announcementList.html?tid_"+StringUtilEx.join(ids, ',');
//	}
//
//	/**
//	 * 得到直购项目文章栏目Url
//	 * @return
//	 */
//	public static String getMallArticleTypeUrl(long[] ids){
//		return getBase()+"/html/announcementList.html?tid_"+StringUtilEx.join(ids, ',')+"|biz_mall";
//	}
//
//	/**
//	 * @return
//	 */
//	public static String getBiddingArticleTypeUrl(String bizStatus,long[] ids){
//		String url = getBase()+"/html/announcementList.html?tid_"+StringUtilEx.join(ids, ',')+"|biz_bidding";
//		if(StringUtilEx.isNotEmpty(bizStatus)){
//			if(bizStatus.equals("published")||bizStatus.equals("publicity")||bizStatus.equals("cancled")||bizStatus.equals("stopped")){
//				url = url+"|s_published,publicity,cancled,stopped";
//			}else{
//				url = url+"|s_"+bizStatus;
//			}
//		}
//		return url;
//	}
//
//	/**
//	 * @return
//	 */
//	public static String getFixArticleTypeUrl(String bizStatus,long[] ids){
//		String url = getBase()+"/html/announcementList.html?tid_"+StringUtilEx.join(ids, ",")+"|biz_fix";
//		if(StringUtilEx.isNotEmpty(bizStatus)){
//			if(bizStatus.equals("published")||bizStatus.equals("publicity")||bizStatus.equals("cancled")||bizStatus.equals("stopped")){
//				url = url+"|s_published,publicity,cancled,stopped";
//			}else{
//				url = url+"|s_"+bizStatus;
//			}
//		}
//		return url;
//	}
//
//	/**
//     * 得到商城商品Url
//     * @param catalogId 平台品目ID
//     * @param productId 商品ID
//     * @return
//     */
//    public static String getBaseMallProductUrl(Long catalogId,Long productId){
//        return "/html/product.html?c_"+catalogId+"|p_"+productId;
//    }
//
//	public static String getBase(){
//		return Constant.mallSiteUrl+Constant.mallSiteCtx;
//	}
//}
