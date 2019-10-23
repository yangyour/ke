package cn.dblearn.dbblog.common.core;

import cn.dblearn.dbblog.common.util.DictUtil;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 下拉框项构建
 * @author developer001
 *
 */
public class SelectOptionBuilder {

	private List<SelectOption> list;

	public SelectOptionBuilder(){
		this.list = Lists.newArrayList();
	}

	/**
	 * 追加下拉项
	 * @param sp
	 */
	public void appendSelectOption(SelectOption sp){
		this.list.add(sp);
	}

	/**
	 * 枚举转下拉项列表
	 * @param enumClass
	 * @return
	 */
	public List<SelectOption> enumToList(Class<?> enumClass){
		Enum<?> arr[] = (Enum[])enumClass.getEnumConstants();
		SelectOption selectOption = null;
		for(int i=0;i<arr.length;i++){
			Enum<?> e = arr[i];
			String label = DictUtil.getString(DictKeyStore.ENUMDICT_PREFIX+enumClass.getSimpleName()+"."+e.name());
			selectOption = new SelectOption(e.name(),label);
			list.add(selectOption);
		}
		return list;
	}

	public void clear(){
		this.list.clear();
	}
}
