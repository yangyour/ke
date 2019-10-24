package cn.dblearn.blog.manage.logs.service.impl;

import cn.dblearn.blog.entity.logs.SysCudLogs;
import cn.dblearn.blog.manage.logs.service.SysCudLogsService;
import cn.dblearn.dbblog.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysCudLogsServiceImpl extends BaseServiceImpl<SysCudLogs> implements SysCudLogsService {

}
