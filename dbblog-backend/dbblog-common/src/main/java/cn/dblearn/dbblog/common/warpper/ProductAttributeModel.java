package cn.dblearn.dbblog.common.warpper;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

/**
 * 商品属性模型
 * @author developer001
 *
 */
@ApiModel(value="商品属性模型")
public class ProductAttributeModel implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ProductAttributeModel(){}

	private Long productId;

	/**
	 * 聚合商家数量
	 */
	private int aggregationNum;


	/**
	 * 是否集采
	 */
	private boolean isCollect;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getAggregationNum() {
		return aggregationNum;
	}

	public void setAggregationNum(int aggregationNum) {
		this.aggregationNum = aggregationNum;
	}

	public boolean isCollect() {
		return isCollect;
	}

	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}

}
