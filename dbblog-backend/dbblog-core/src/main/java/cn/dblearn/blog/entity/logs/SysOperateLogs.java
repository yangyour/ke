package cn.dblearn.blog.entity.logs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.dblearn.blog.common.enums.SysOperateLogType;
import cn.dblearn.dbblog.common.base.BaseEntity;
import cn.dblearn.dbblog.common.context.log.ContextOperateLog;

import cn.dblearn.dbblog.common.util.DateUtilEx;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SysOperateLogs (table = sys_operate_logs)
 *
 * 系统日志
 */
@Data
@ApiModel(value="系统日志")
@Table(name = "sys_operate_logs")
public class SysOperateLogs extends BaseEntity implements ContextOperateLog {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="")
    @Column(name = "created_at")
    private Date createdAt;

    @ApiModelProperty(value="")
    @Column(name = "updated_at")
    private Date updatedAt;

    @ApiModelProperty(value="事件描述")
    @Column(name = "description")
    private String description;

    @ApiModelProperty(value="操作者")
    @Column(name = "operator")
    private String operator;

    @ApiModelProperty(value="操作人单位名称")
    @Column(name = "operator_dep")
    private String operatorDep;

    @ApiModelProperty(value="")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value="")
    @Column(name = "event")
    private String event;

    @ApiModelProperty(value="日志类型 :操作日志 、登录日志")
    @Column(name = "log_type")
    private SysOperateLogType logType;

    @ApiModelProperty(value="ip")
    @Column(name = "ip")
    private String ip;

    @ApiModelProperty(value="用户名")
    @Column(name = "login_name")
    private String loginName;



    /**
     *
     * @return Date
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Set the createdAt
     */
    public void setCreatedAt(Date aValue) {
        this.createdAt = aValue;
    }
    /**
     *
     * @return Date
     */
    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Set the updatedAt
     */
    public void setUpdatedAt(Date aValue) {
        this.updatedAt = aValue;
    }
    /**
     * 事件描述
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description
     */
    public void setDescription(String aValue) {
        this.description = aValue;
    }
    /**
     * 操作者
     * @return String
     */
    public String getOperator() {
        return this.operator;
    }

    /**
     * Set the operator
     */
    public void setOperator(String aValue) {
        this.operator = aValue;
    }
    /**
     * 操作人单位名称
     * @return String
     */
    public String getOperatorDep() {
        return this.operatorDep;
    }

    /**
     * Set the operatorDep
     */
    public void setOperatorDep(String aValue) {
        this.operatorDep = aValue;
    }
    /**
     *
     * @return Long
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * Set the userId
     */
    public void setUserId(Long aValue) {
        this.userId = aValue;
    }
    /**
     *
     * @return String
     */
    public String getEvent() {
        return this.event;
    }

    /**
     * Set the event
     */
    public void setEvent(String aValue) {
        this.event = aValue;
    }
    /**
     * 日志类型 :操作日志 、登录日志
     * @return SysOperateLogType
     */
    public SysOperateLogType getLogType() {
        return this.logType;
    }

    /**
     * Set the logType
     */
    public void setLogType(SysOperateLogType aValue) {
        this.logType = aValue;
    }
    /**
     * ip
     * @return String
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Set the ip
     */
    public void setIp(String aValue) {
        this.ip = aValue;
    }
    /**
     * 用户名
     * @return String
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * Set the loginName
     */
    public void setLoginName(String aValue) {
        this.loginName = aValue;
    }

    @Override
    public boolean isCudLog() {
        return false;
    }

    /**
     * 获取格式化日期
     * @return
     */
    @ApiModelProperty(value="获取格式化日期")
    public String getCreatedAtFormat(){
        if(getCreatedAt()!=null){
            return DateUtilEx.timeFormat(getCreatedAt());
        }
        return null;
    }
}

