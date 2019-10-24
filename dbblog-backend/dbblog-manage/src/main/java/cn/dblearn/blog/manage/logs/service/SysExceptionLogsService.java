package cn.dblearn.blog.manage.logs.service;

import cn.dblearn.blog.entity.logs.SysExceptionLogs;
import cn.dblearn.dbblog.common.base.BaseService;
import cn.dblearn.dbblog.common.core.ISysExceptionLogsService;

import java.util.List;

public interface SysExceptionLogsService extends BaseService<SysExceptionLogs>, ISysExceptionLogsService {
    List<SysExceptionLogs> findListByEntity(SysExceptionLogs entity);
}
