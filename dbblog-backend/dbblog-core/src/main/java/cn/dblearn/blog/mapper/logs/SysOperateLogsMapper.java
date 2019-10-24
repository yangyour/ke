package cn.dblearn.blog.mapper.logs;


import cn.dblearn.blog.entity.logs.SysOperateLogs;
import cn.dblearn.dbblog.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//mapper注解
@Mapper
public interface SysOperateLogsMapper extends BaseMapper<SysOperateLogs> {

    /**
     * 条件查询日志列表
     *
     * @param entity
     * @return
     */
    List<SysOperateLogs> findListByEntity(SysOperateLogs entity);

    List<SysOperateLogs> findAll();

    /**
     * 查询单条详情
     *
     * @param entity
     * @return
     */
    SysOperateLogs selectById(Long id);
}
