package com.spring.aspect.log.model;

/**
 * Api 接口 日志记录
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/5/2 17:15
 * @since 0.1
 */
public class ApiLog extends Log {
    /**
     * 请求开始时间
     */
    private long startTime;
    /**
     * 请求结束时间
     */
    private long endTime;
    /**
     * 执行时间
     */
    private long executeTime;
    /**
     * 请求类型
     */
    private String requestContentType;
    /**
     * 请求内容
     */
    private String requestContent;
    /**
     * 返回信息
     */
    private String responseInfo;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public String getRequestContentType() {
        return requestContentType;
    }

    public void setRequestContentType(String requestContentType) {
        this.requestContentType = requestContentType;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    @Override
    public String toString() {
        return "ApiLog{" + "startTime=" + startTime + ", endTime=" + endTime + ", executeTime=" + executeTime + ", requestContentType='" + requestContentType + '\'' + ", requestContent='" + requestContent + '\'' + ", responseInfo='" + responseInfo + '\'' + ", id=" + id + ", reqUrl='" + reqUrl + '\'' + ", event='" + event + '\'' + ", clazz='" + clazz + '\'' + ", method='" + method + '\'' + ", clientIp='" + clientIp + '\'' + ", status=" + status + '}';
    }
}
