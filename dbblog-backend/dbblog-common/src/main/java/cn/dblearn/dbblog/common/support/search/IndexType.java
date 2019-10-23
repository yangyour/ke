package cn.dblearn.dbblog.common.support.search;

/**
 * spe 索引
 * @author developer001
 */
public enum IndexType{
	products("interimproduct","product"), //商品
	supplier("interimsupplier","supplier"), //供应商/店铺
	article("interimarticle","article");//文章

	IndexType(String indexName,String mappingName){
		this.indexName=indexName;
		this.mappingName = mappingName;
	}
	String indexName;
	String mappingName;

	/**
	 * 获取索引名
	 * @return
	 */
	public String getIndexName(){
		return indexName;
	}

	/**
	 * 获取映射名字
	 * @return
	 */
	public String getMappingName(){
		return mappingName;
	}
}
