package cn.dblearn.dbblog.common.support.search;

import cn.dblearn.dbblog.common.support.search.model.QuotationModel;

public interface IQuotationSearchService {

	/**
 	 * 根据数据库ID查询索引
 	 * @param id
 	 * @return
 	 */
 	String findIndexId(long id);

	/**
	 * 添加
	 */
	boolean addData(QuotationModel quotation);

	/**
	 * 删除
	 * @param indexId
	 * @return
	 */
	boolean deleteData(String indexId);

	/**
	 * 是否启用
	 * @return
	 */
	boolean isEnable();
}
