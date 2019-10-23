package cn.dblearn.dbblog.common.support.search;

import java.util.Map;
import java.util.Set;

import cn.dblearn.dbblog.common.support.search.model.ProductModel;
import cn.dblearn.dbblog.common.warpper.PageBean;

public interface IProductSearchService {

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
 	 * 根据数据库ID查询索引
 	 * @param id
 	 * @return
 	 */
 	String findIndexId(long id);

	/**
	 * 添加
	 */
	boolean addData(ProductModel product);

	/**
	 * 修改
	 * @param indexId
	 * @param product
	 * @return
	 */
	boolean updateData(String indexId, ProductModel product);

	/**
	 * 删除
	 * @param indexId
	 * @return
	 */
	boolean deleteData(String indexId);

	/**
	 * 产品搜索
	 * @param keywords
	 * @param page
	 * @param usableSupplierIds 可用供应商ids
	 * @return
	 */
	IProductResponse searcherProduct(String keywords, PageBean page, Set<Long> usableSupplierIds, Map<String, Object> queryfilter);

	/**
	 * 是否启用
	 * @return
	 */
	boolean isEnable();
}
