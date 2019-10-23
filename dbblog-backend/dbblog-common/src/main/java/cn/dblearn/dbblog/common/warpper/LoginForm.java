package cn.dblearn.dbblog.common.warpper;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="登录")
public class LoginForm implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	 /**
     * 用户类型：0:采购办，1：采购单位，2：供应商
     */
    @ApiModelProperty(value="用户类型：0:采购办，1：采购单位，2：供应商")
    private Integer type;

	@ApiModelProperty(value="登录帐号")
	private String uname;

	@ApiModelProperty(value="登录密码")
	private String pwd;

	@ApiModelProperty(value="记住我")
	private Boolean rememberMe;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
