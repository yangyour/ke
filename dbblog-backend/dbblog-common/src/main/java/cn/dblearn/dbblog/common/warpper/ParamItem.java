package cn.dblearn.dbblog.common.warpper;

import java.io.Serializable;

/**
 * 商品项
 * @author developer001
 */
public class ParamItem implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String name;//名称

	private String value;//参数值

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
