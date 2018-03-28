package com.spring.aspect.log.model;

import java.io.Serializable;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/20 13:55
 * @since 0.1
 */
public class Log implements Serializable {

    private static final long serialVersionUID = -1L;
    /**
     * 主键
     */
    protected Long id;
    /**
     * 访问url
     */
    protected String reqUrl;
    /**
     * 事件（删除，新增，修改，查询，登录，退出）
     */
    protected String event;
    /**
     * 请求类
     */
    protected String clazz;
    /**
     * 请求方法
     */
    protected String method;
    /**
     * 客户端IP
     */
    protected String clientIp;
    /**
     * 状态（0：失败，1：成功）
     */
    protected int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
