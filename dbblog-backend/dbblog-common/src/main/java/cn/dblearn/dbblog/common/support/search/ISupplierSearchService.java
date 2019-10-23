package cn.dblearn.dbblog.common.support.search;

import java.util.Set;

import cn.dblearn.dbblog.common.support.search.model.SupplierModel;

public interface ISupplierSearchService {

	/**
	 * 创建引库
	 */
    void createIndex();

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
	 * 搜索可用供应商ids
	 * @return
	 */
	Set<Long> searchUsableSupplierIds();

	/**
 	 * 根据数据库ID查询索引
 	 * @param id
 	 * @return
 	 */
 	String findIndexId(long id);

	/**
	 * 添加
	 */
	boolean addData(SupplierModel supplier);

	/**
	 * 修改
	 * @param indexId
	 * @param supplier
	 * @return
	 */
	boolean updateData(String indexId, SupplierModel supplier);

	/**
	 * 是否启用
	 * @return
	 */
	boolean isEnable();
}
