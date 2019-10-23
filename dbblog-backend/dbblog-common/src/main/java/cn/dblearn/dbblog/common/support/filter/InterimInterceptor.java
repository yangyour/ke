package cn.dblearn.dbblog.common.support.filter;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public abstract class InterimInterceptor extends HandlerInterceptorAdapter implements Comparable<InterimInterceptor>{
	/**
	 * 获取ID
	 *
	 * @return ID
	 */
	public final String getId() {
		return getClass().getAnnotation(Component.class).value();
	}

	/**
	 * 获取排序
	 *
	 * @return 排序
	 */
	public abstract Integer getSort();

	@Override
	public int compareTo(InterimInterceptor interimInterceptor) {
		return new CompareToBuilder().append(getSort(), interimInterceptor.getSort()).append(getId(), interimInterceptor.getId()).toComparison();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		InterimInterceptor other = (InterimInterceptor) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
	}
}
