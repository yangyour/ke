package cn.dblearn.dbblog.common.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 系统设置
 * @author developer001
 */
@ApiModel(value="系统设置")
public class Setting implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 小数位精确方式
	 */
	public enum RoundType {

		/** 四舍五入 */
		roundHalfUp,

		/** 向上取整 */
		roundUp,

		/** 向下取整 */
		roundDown
	}

	@ApiModelProperty("热门搜索，多个英文逗号隔开")
	private String hotSearch;

	@ApiModelProperty("技术支持热线，多个英文逗号隔开")
	private String techHotline;

	@ApiModelProperty("业务资询热线，多个英文逗号隔开")
	private String bizHotline;

	@ApiModelProperty("版权信息")
	private String copyright;

	@ApiModelProperty("备案编号")
	private String certtext;

	@ApiModelProperty("邮编")
	private String zipcode;

	@ApiModelProperty("技术支持")
	private String techSupport;

	@ApiModelProperty("是否启用电商接口")
	private boolean isEnableEmallApi;

	@ApiModelProperty("订单确认等待时间,单位:分钟")
	private Integer orderConfirmWaitTime;

	@ApiModelProperty("最大查询数量")
	private int maxQueryCount;

	@ApiModelProperty("计划项最大限额数量")
	private BigDecimal planItemMaxAmount;

	@ApiModelProperty("金额精确位数")
	private Integer amountScale;

	@ApiModelProperty("金额精确方式")
	private RoundType amountRoundType;

	@ApiModelProperty("订单延迟自动完成间隔天数")
	private Integer orderDelayAutoCompletedDays;

	@ApiModelProperty("商城计划项金额边界")
	private BigDecimal mallPlanItemAmountBorder;

	public String getHotSearch() {
		return hotSearch;
	}

	public void setHotSearch(String hotSearch) {
		this.hotSearch = hotSearch;
	}

	public String getTechHotline() {
		return techHotline;
	}

	public void setTechHotline(String techHotline) {
		this.techHotline = techHotline;
	}

	public String getBizHotline() {
		return bizHotline;
	}

	public void setBizHotline(String bizHotline) {
		this.bizHotline = bizHotline;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getCerttext() {
		return certtext;
	}

	public void setCerttext(String certtext) {
		this.certtext = certtext;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTechSupport() {
		return techSupport;
	}

	public void setTechSupport(String techSupport) {
		this.techSupport = techSupport;
	}

	public boolean getIsEnableEmallApi() {
		return isEnableEmallApi;
	}

	public void setEnableEmallApi(boolean isEnableEmallApi) {
		this.isEnableEmallApi = isEnableEmallApi;
	}

	public void setIsEnableEmallApi(boolean isEnableEmallApi) {
		this.isEnableEmallApi = isEnableEmallApi;
	}

	public Integer getOrderConfirmWaitTime() {
		return orderConfirmWaitTime;
	}

	public void setOrderConfirmWaitTime(Integer orderConfirmWaitTime) {
		this.orderConfirmWaitTime = orderConfirmWaitTime;
	}

	public int getMaxQueryCount() {
		return maxQueryCount;
	}

	public void setMaxQueryCount(int maxQueryCount) {
		this.maxQueryCount = maxQueryCount;
	}

	public BigDecimal getPlanItemMaxAmount() {
		return planItemMaxAmount;
	}

	public void setPlanItemMaxAmount(BigDecimal planItemMaxAmount) {
		this.planItemMaxAmount = planItemMaxAmount;
	}

	public Integer getAmountScale() {
		return amountScale;
	}

	public void setAmountScale(Integer amountScale) {
		this.amountScale = amountScale;
	}

	public RoundType getAmountRoundType() {
		return amountRoundType;
	}

	public void setAmountRoundType(RoundType amountRoundType) {
		this.amountRoundType = amountRoundType;
	}

	public void setOrderDelayAutoCompletedDays(Integer orderDelayAutoCompletedDays) {
		this.orderDelayAutoCompletedDays = orderDelayAutoCompletedDays;
	}

	public BigDecimal getMallPlanItemAmountBorder() {
		return mallPlanItemAmountBorder;
	}

	public void setMallPlanItemAmountBorder(BigDecimal mallPlanItemAmountBorder) {
		this.mallPlanItemAmountBorder = mallPlanItemAmountBorder;
	}

	public Integer getOrderDelayAutoCompletedDays() {
		return orderDelayAutoCompletedDays;
	}

	/**
	 * 是否订单自动完成
	 * @return
	 */
	@JsonIgnoreProperties
	public boolean isOrderDelayAutoCompleted(){
		if(getOrderDelayAutoCompletedDays()!=null&&getOrderDelayAutoCompletedDays()>0){
			return true;
		}
		return false;
	}
}
