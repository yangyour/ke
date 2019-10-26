package cn.dblearn.blog.manage.utils;

import cn.dblearn.blog.entity.logs.SysCudLogs;
import cn.dblearn.blog.entity.logs.SysOperateLogs;
import cn.dblearn.blog.manage.logs.service.SysCudLogsService;
import cn.dblearn.dbblog.common.context.log.ContextOperateLog;
import cn.dblearn.dbblog.common.context.log.CudLogManager;
import cn.dblearn.dbblog.common.context.log.CudType;
import cn.dblearn.dbblog.common.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统操作日志
 * @author developer001
 *
 */
@Component
public class SysOptManager implements CudLogManager {

	private ContextOperateLog contextOperateLog;

	@Autowired
	private SysCudLogsService sysCudLogsService;

	@Transactional
	@Override
	public void excuteLog(Object before, Object after, CudType type) {
		if(getContextOperateLog() instanceof SysOperateLogs){
			SysOperateLogs sysOperateLogs = (SysOperateLogs)getContextOperateLog();
			String dataContent = null;

			Class<? extends Object> entityClass = null;

			Table table = null;
			if(after!=null){
				table = after.getClass().getAnnotation(Table.class);
				entityClass = after.getClass();
			}else{
				table = before.getClass().getAnnotation(Table.class);
				entityClass = before.getClass();
			}
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

			String tableName = table!=null?table.name():entityClass.getSimpleName().toLowerCase();

			if(type==CudType.create){
				dataContent = LogUtil.objToJsonString(after);
			}else if(type==CudType.update){
				dataContent = LogUtil.contrastIdenticalObj(before, after);
			}else if(type==CudType.delete){
				dataContent = LogUtil.objToJsonString(before);
			}

			String tableComment = null;
			SysCudLogs cudLog = new SysCudLogs();
			cudLog.setOperateLogsId(sysOperateLogs.getId());
			cudLog.setDataContent(dataContent);
			cudLog.setType(type.toString());
			cudLog.setTableName(tableName);
			cudLog.setTableComment(tableComment);
			cudLog.setEntityClass(entityClass.getName());
			sysCudLogsService.save(cudLog);
		}

	}


	@Override
	public ContextOperateLog getContextOperateLog() {
		return contextOperateLog;
	}


	public void setContextOperateLog(ContextOperateLog log) {
		this.contextOperateLog = log;
	}


}
