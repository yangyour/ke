package cn.dblearn.blog.manage.logs.service.impl;

import cn.dblearn.blog.entity.logs.SysOperateLogs;
import cn.dblearn.blog.manage.logs.service.SysOperateLogsService;
import cn.dblearn.dbblog.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysOperateLogsServiceImpl extends BaseServiceImpl<SysOperateLogs> implements SysOperateLogsService {

//    @Autowired
//    private SysExceptionLogsMapper sysExceptionLogsMapper;


}
