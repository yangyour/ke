package cn.dblearn.blog.manage.logs.service.impl;

import cn.dblearn.blog.entity.logs.SysExceptionLogs;
import cn.dblearn.blog.manage.logs.service.SysExceptionLogsService;
import cn.dblearn.blog.mapper.logs.SysExceptionLogsMapper;
import cn.dblearn.dbblog.common.base.BaseServiceImpl;
import cn.dblearn.dbblog.common.core.ExceptionLogBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysExceptionLogsServiceImpl extends BaseServiceImpl<SysExceptionLogs> implements SysExceptionLogsService {

    @Autowired
    private SysExceptionLogsMapper sysExceptionLogsMapper;

    @Override
    public int saveLog(ExceptionLogBean log) {
        if (log != null) {
            SysExceptionLogs entity = new SysExceptionLogs();
            BeanUtils.copyProperties(log, entity);
            boolean save = save(entity);
            int a=0;
            if (save==true){
                a=1;
            }
            return a;
        }
        return 0;
    }

    @Override
    public List<SysExceptionLogs> findListByEntity(SysExceptionLogs entity) {
        return sysExceptionLogsMapper.findListByEntity(entity);
    }



}
