package cn.dblearn.dbblog.common.warpper;

import java.util.Map;

import io.swagger.annotations.ApiModelProperty;


public class MallApiVo {
    @ApiModelProperty(value="grantType")
    private String grantType;

    @ApiModelProperty(value="clientId")
    private String clientId;

    @ApiModelProperty(value="clientSecret")
    private String clientSecret;

    @ApiModelProperty(value="userName")
    private String userName;

    @ApiModelProperty(value="password")
    private String password;

    @ApiModelProperty(value="scope")
    private String scope;

    @ApiModelProperty(value="emallCode")
    private String emallCode;

    @ApiModelProperty(value="code")
    private String code;

    @ApiModelProperty(value="redirectUri")
    private String redirectUri;

    @ApiModelProperty(value="state")
    private String state;


    /**
     * 电商接口key=code 和接口地址
     */
    private Map<String, String> mapUrl;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Map<String, String> getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(Map<String, String> mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getEmallCode() {
        return emallCode;
    }

    public void setEmallCode(String emallCode) {
        this.emallCode = emallCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }




}
