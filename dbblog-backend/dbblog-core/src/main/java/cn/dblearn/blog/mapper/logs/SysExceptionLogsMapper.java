package cn.dblearn.blog.mapper.logs;
/*
 *  大庆市政府采购系统
 *  Copyright (C) 2018-  2020  SOFTWARES LIMITED COMPANY
 */


import cn.dblearn.blog.entity.logs.SysExceptionLogs;
import cn.dblearn.dbblog.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysExceptionLogsMapper extends BaseMapper<SysExceptionLogs> {

    /**
     * 条件查询列表
     *
     * @param entity
     * @return
     */
    List<SysExceptionLogs> findListByEntity(SysExceptionLogs entity);

    /**
     * 单条详情查询
     *
     * @param id
     * @return
     */
    SysExceptionLogs selectById(Long id);
}
