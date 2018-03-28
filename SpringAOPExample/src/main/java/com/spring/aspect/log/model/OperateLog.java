package com.spring.aspect.log.model;

import java.util.Date;

/**
 * 用户 日志记录
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/20 13:50
 * @since 0.1
 */
public class OperateLog extends Log {
    /**
     * 操作用户ID
     */
    private Integer userId;
    /**
     * 操作人名称
     */
    private String userName;
    /**
     * 操作时间(yyyy-MM-dd HH:mm:ss)
     */
    private Date operateTime;
    /**
     * 描述信息
     */
    private String description;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OperateLog{" + "userId=" + userId + ", userName='" + userName + '\'' + ", operateTime=" + operateTime + ", description='" + description + '\'' + ", id=" + id + ", reqUrl='" + reqUrl + '\'' + ", event='" + event + '\'' + ", clazz='" + clazz + '\'' + ", method='" + method + '\'' + ", clientIp='" + clientIp + '\'' + ", status=" + status + '}';
    }
}
