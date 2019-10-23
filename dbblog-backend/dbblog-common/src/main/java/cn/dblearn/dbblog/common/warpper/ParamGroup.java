package cn.dblearn.dbblog.common.warpper;

import java.io.Serializable;
import java.util.List;

import cn.dblearn.dbblog.common.base.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品组
 * @author developer001
 */
@ApiModel(value="商品组")
public class ParamGroup implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="组名称")
	private String name;//名称

	@ApiModelProperty(value="参数项")
	private List<ParamItem> paramItems;//参数项

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ParamItem> getParamItems() {
		return paramItems;
	}

	public void setParamItems(List<ParamItem> paramItems) {
		this.paramItems = paramItems;
	}

	/**
	 * 重写equals方法
	 *
	 * @param obj
	 *            对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		ParamGroup other = (ParamGroup) obj;
		return getName() != null ? getName().equals(other.getName()) : false;
	}

	/**
	 * 重写hashCode方法
	 *
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getName() ? 0 : getName().hashCode() * 31;
		return hashCode;
	}
}
