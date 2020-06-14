package com.davidche.appfabric.uaa.log.system.model;

import com.davidche.appfabric.uaa.util.MyStringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 日志类-记录用户操作行为
 * https://blog.csdn.net/myron_007/article/details/54927529
 * https://github.com/MusicXi/demo-aop-log
 *
 * @author lin.r.x
 */
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;

    private String logId;            //日志主键
    private String type;            //日志类型
    private String title;            //日志标题
    private String remoteAddr;            //请求地址
    private String requestUri;            //URI
    private String method;            //请求方式
    private String params;            //提交参数
    private String exception;            //异常
    private Date operateDate;            //开始时间
    private String timeout;            //结束时间
    private String userId;            //用户ID

    public String getLogId() {
        return MyStringUtils.isBlank(logId) ? logId : logId.trim();
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }


    public String getType() {
        return MyStringUtils.isBlank(type) ? type : type.trim();
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTitle() {
        return MyStringUtils.isBlank(title) ? title : title.trim();
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getRemoteAddr() {
        return MyStringUtils.isBlank(remoteAddr) ? remoteAddr : remoteAddr.trim();
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }


    public String getRequestUri() {
        return MyStringUtils.isBlank(requestUri) ? requestUri : requestUri.trim();
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }


    public String getMethod() {
        return MyStringUtils.isBlank(method) ? method : method.trim();
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public String getParams() {
        return MyStringUtils.isBlank(params) ? params : params.trim();
    }

    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 设置请求参数
     *
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {
        if (paramMap == null) {
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.append(paramValue);
//            params.append(MyStringUtils.abbr(MyStringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
        }
        this.params = params.toString();
    }


    public String getException() {
        return MyStringUtils.isBlank(exception) ? exception : exception.trim();
    }

    public void setException(String exception) {
        this.exception = exception;
    }


    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }


    public String getTimeout() {
        return MyStringUtils.isBlank(timeout) ? timeout : timeout.trim();
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }


    public String getUserId() {
        return MyStringUtils.isBlank(userId) ? userId : userId.trim();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Log [logId=" + logId + ", type=" + type + ", title=" + title
                + ", remoteAddr=" + remoteAddr + ", requestUri=" + requestUri
                + ", method=" + method + ", params=" + params + ", exception="
                + exception + ", operateDate=" + operateDate + ", timeout="
                + timeout + ", userId=" + userId + "]";
    }


}