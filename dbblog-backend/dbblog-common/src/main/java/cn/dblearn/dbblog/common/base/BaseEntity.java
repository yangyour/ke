package cn.dblearn.dbblog.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Map;

public abstract class BaseEntity implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

	@Transient
	@JsonIgnore
	private Map<String,Object> queryfilter;//查询过滤器

	@Transient
	@JsonIgnore
	private boolean isCudLog = true;//是否增改删日志记录,默认是

	@Transient
	@JsonIgnore
	private boolean isAmountProcess = true;//是否金额系统加工,默认是

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, Object> getQueryfilter() {
		return queryfilter;
	}

	public void setQueryfilter(Map<String, Object> queryfilter) {
		this.queryfilter = queryfilter;
	}

	@JsonIgnore
	public boolean isAmountProcess() {
		return isAmountProcess;
	}

	public void setAmountProcess(boolean isAmountProcess) {
		this.isAmountProcess = isAmountProcess;
	}

	public boolean isCudLog() {
		return isCudLog;
	}

	@JsonIgnore
	public void setCudLog(boolean isCudLog) {
		this.isCudLog = isCudLog;
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
		BaseEntity other = (BaseEntity) obj;
		return getId() != null ? getId().equals(other.getId()) : false;
	}

	/**
	 * 重写hashCode方法
	 *
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}


}
