package com.lzlk.dao.mybatis.admin.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * admin_request_log
 * @author 
 */
public class AdminRequestLogDo implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户id
     */
    private Long adminUserId;

    /**
     * 登录IP
     */
    private String signIp;

    /**
     * 访问路径
     */
    private String requestUrl;

    /**
     * 访问参数
     */
    private String args;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getSignIp() {
        return signIp;
    }

    public void setSignIp(String signIp) {
        this.signIp = signIp;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AdminRequestLogDo other = (AdminRequestLogDo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getAdminUserId() == null ? other.getAdminUserId() == null : this.getAdminUserId().equals(other.getAdminUserId()))
            && (this.getSignIp() == null ? other.getSignIp() == null : this.getSignIp().equals(other.getSignIp()))
            && (this.getRequestUrl() == null ? other.getRequestUrl() == null : this.getRequestUrl().equals(other.getRequestUrl()))
            && (this.getArgs() == null ? other.getArgs() == null : this.getArgs().equals(other.getArgs()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getAdminUserId() == null) ? 0 : getAdminUserId().hashCode());
        result = prime * result + ((getSignIp() == null) ? 0 : getSignIp().hashCode());
        result = prime * result + ((getRequestUrl() == null) ? 0 : getRequestUrl().hashCode());
        result = prime * result + ((getArgs() == null) ? 0 : getArgs().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", adminUserId=").append(adminUserId);
        sb.append(", signIp=").append(signIp);
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", args=").append(args);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}