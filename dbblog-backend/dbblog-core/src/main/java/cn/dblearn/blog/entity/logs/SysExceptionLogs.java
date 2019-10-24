package cn.dblearn.blog.entity.logs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.dblearn.dbblog.common.base.BaseEntity;
import cn.dblearn.dbblog.common.context.log.ContextOperateLog;

import cn.dblearn.dbblog.common.util.DateUtilEx;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SysExceptionLogs (table = sys_exception_logs)
 *
 * 异常日志表
 */
@Data
@ApiModel(value="异常日志表")
@Table(name = "sys_exception_logs")
public class SysExceptionLogs extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="")
    @Column(name = "created_at")
    private Date createdAt;

    @ApiModelProperty(value="")
    @Column(name = "updated_at")
    private Date updatedAt;

    @ApiModelProperty(value="异常标题")
    @Column(name = "title")
    private String title;

    @ApiModelProperty(value="异常名")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value="异常message信息")
    @Column(name = "message")
    private String message;

    @ApiModelProperty(value="控制类")
    @Column(name = "controller")
    private String controller;

    @ApiModelProperty(value="控制类方法")
    @Column(name = "controller_method")
    private String controllerMethod;

    @ApiModelProperty(value="参数")
    @Column(name = "params")
    private String params;

    @ApiModelProperty(value="异常堆栈信息")
    @Column(name = "stack_trace")
    private String stackTrace;

    @ApiModelProperty(value="请求方式 GET POST")
    @Column(name = "http_method")
    private String httpMethod;

    @ApiModelProperty(value="请求IP")
    @Column(name = "ip")
    private String ip;

    @ApiModelProperty(value="本机IP")
    @Column(name = "local_ip")
    private String localIp;

    @ApiModelProperty(value="用户名")
    @Column(name = "login_name")
    private String loginName;

    @ApiModelProperty(value="请求url")
    @Column(name = "http_url")
    private String httpUrl;

    @ApiModelProperty(value="")
    @Column(name = "user_id")
    private String userId;



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
     * 异常标题
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the title
     */
    public void setTitle(String aValue) {
        this.title = aValue;
    }
    /**
     * 异常名
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name
     */
    public void setName(String aValue) {
        this.name = aValue;
    }
    /**
     * 异常message信息
     * @return String
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message
     */
    public void setMessage(String aValue) {
        this.message = aValue;
    }
    /**
     * 控制类
     * @return String
     */
    public String getController() {
        return this.controller;
    }

    /**
     * Set the controller
     */
    public void setController(String aValue) {
        this.controller = aValue;
    }
    /**
     * 控制类方法
     * @return String
     */
    public String getControllerMethod() {
        return this.controllerMethod;
    }

    /**
     * Set the controllerMethod
     */
    public void setControllerMethod(String aValue) {
        this.controllerMethod = aValue;
    }
    /**
     * 参数
     * @return String
     */
    public String getParams() {
        return this.params;
    }

    /**
     * Set the params
     */
    public void setParams(String aValue) {
        this.params = aValue;
    }
    /**
     * 异常堆栈信息
     * @return String
     */
    public String getStackTrace() {
        return this.stackTrace;
    }

    /**
     * Set the stackTrace
     */
    public void setStackTrace(String aValue) {
        this.stackTrace = aValue;
    }
    /**
     * 请求方式 GET POST
     * @return String
     */
    public String getHttpMethod() {
        return this.httpMethod;
    }

    /**
     * Set the httpMethod
     */
    public void setHttpMethod(String aValue) {
        this.httpMethod = aValue;
    }
    /**
     * 请求IP
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
     * 本机IP
     * @return String
     */
    public String getLocalIp() {
        return this.localIp;
    }

    /**
     * Set the localIp
     */
    public void setLocalIp(String aValue) {
        this.localIp = aValue;
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
    /**
     * 请求url
     * @return String
     */
    public String getHttpUrl() {
        return this.httpUrl;
    }

    /**
     * Set the httpUrl
     */
    public void setHttpUrl(String aValue) {
        this.httpUrl = aValue;
    }
    /**
     *
     * @return String
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Set the userId
     */
    public void setUserId(String aValue) {
        this.userId = aValue;
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
