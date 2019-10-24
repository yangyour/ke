package cn.dblearn.dbblog.common.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

}
