package cn.dblearn.dbblog.common.warpper;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 供应商注册表单
 * @author lxh
 *
 */
public class RegisterForm implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="供应商名称")
	private String name;

	@ApiModelProperty(value="供应商规模 (1小微型企业 2中型企业 3大型企业)")
	private String type;

	@ApiModelProperty(value="联系人姓名")
	private String contactsName;

	@ApiModelProperty(value="联系人手机号")
	private String contactsMobile;

	@ApiModelProperty(value="验证码")
	private String verifyCode;

	@ApiModelProperty(value="统一社会信用代码")
	private String creditCode;

	@ApiModelProperty(value="供应商logo")
	private String logoUrl;

	@ApiModelProperty(value="新统一社会信用代码")
	private String newCreditCode;

	@ApiModelProperty(value="地区id")
	private Long areaId;

	@ApiModelProperty(value="详细地址")
	private String addr;

	@ApiModelProperty(value="开户银行")
	private String openingBank;

	@ApiModelProperty(value="银行账号")
	private String  bankAccount;

	@ApiModelProperty(value="附件名称")
	private String fileName;

	@ApiModelProperty(value="附件url")
	private String fileUrl;

	@ApiModelProperty(value="销售类别")
	private Long[] saleType;

	@ApiModelProperty(value="销售品目")
	private String saleCatalog;

	@ApiModelProperty(value="供应商类别 1电商 0普通供应商")
	private Integer mallType;




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContactsMobile() {
		return contactsMobile;
	}

	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Long[] getSaleType() {
		return saleType;
	}

	public void setSaleType(Long[] saleType) {
		this.saleType = saleType;
	}

	public String getSaleCatalog() {
		return saleCatalog;
	}

	public void setSaleCatalog(String saleCatalog) {
		this.saleCatalog = saleCatalog;
	}

	public String getNewCreditCode() {
		return newCreditCode;
	}

	public void setNewCreditCode(String newCreditCode) {
		this.newCreditCode = newCreditCode;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}


	public Integer getMallType() {
		return mallType;
	}

	public void setMallType(Integer mallType) {
		this.mallType = mallType;
	}
}
