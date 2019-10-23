package cn.dblearn.dbblog.common.support.search;


import java.util.Map;

import cn.dblearn.dbblog.common.support.search.model.ArticleModel;
import cn.dblearn.dbblog.common.warpper.PageBean;

public interface IArticleSearchSerivce {

	/**
	 * 创建引库
	 */
    public void createIndex();

    /**
     * 判断索引是否存在
     * @return
     */
    boolean isIndexExists();

    /**
	 * 创建Mapping
	 */
	void createMapping();

	/**
	 * 搜索
	 * @param keywords
	 * @param page
	 * @return
	 */
	IArticleResponse search(String keywords, PageBean page, Map<String, Object> queryfilter);

	/**
 	 * 根据数据库ID查询索引
 	 * @param id
 	 * @return
 	 */
 	String findIndexId(long id);

	/**
	 * 添加
	 */
	boolean addData(ArticleModel article);

	/**
	 * 修改
	 * @param indexId
	 * @param article
	 * @return
	 */
	boolean updateData(String indexId, ArticleModel article);

	/**
	 * 是否启用
	 * @return
	 */
	boolean isEnable();
}
