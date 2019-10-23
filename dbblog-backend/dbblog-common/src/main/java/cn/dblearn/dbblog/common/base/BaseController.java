package cn.dblearn.dbblog.common.base;

import cn.dblearn.dbblog.common.Constant;
import cn.dblearn.dbblog.common.core.Setting;
import cn.dblearn.dbblog.common.core.SettingUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

public class BaseController  {

	/**
	 * 初始化数据绑定
	 *   将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				try {
					if (StringUtils.isNotEmpty(text)) {
						setValue(DateUtils.parseDate(text, Constant.DATE_PATTERNS));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected Integer getQueryCount(Integer count){
		if(count!=null&&count>10){//超过10个做最大查询数量校验
			Setting setting = SettingUtil.get();
			if(count>setting.getMaxQueryCount()){
				count = setting.getMaxQueryCount();
			}
		}
		return count;
	}
}
