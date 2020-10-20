package com.lzlk.dao.mybatis.system.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * system_parameter_config
 * @author 
 */
public class SystemParameterConfigDo implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 创建人
     */
    private Long createAdminId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更改人
     */
    private Long lastModifyAdminId;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    /**
     * 参数名称
     */
    private String parameterKey;

    /**
     * 参数值
     */
    private String parameterValue;

    /**
     * 这个参数的介绍和描述
     */
    private String remark;

    /**
     * 删除状态
     */
    private Boolean isDelete;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateAdminId() {
        return createAdminId;
    }

    public void setCreateAdminId(Long createAdminId) {
        this.createAdminId = createAdminId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyAdminId() {
        return lastModifyAdminId;
    }

    public void setLastModifyAdminId(Long lastModifyAdminId) {
        this.lastModifyAdminId = lastModifyAdminId;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getParameterKey() {
        return parameterKey;
    }

    public void setParameterKey(String parameterKey) {
        this.parameterKey = parameterKey;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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
        SystemParameterConfigDo other = (SystemParameterConfigDo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateAdminId() == null ? other.getCreateAdminId() == null : this.getCreateAdminId().equals(other.getCreateAdminId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastModifyAdminId() == null ? other.getLastModifyAdminId() == null : this.getLastModifyAdminId().equals(other.getLastModifyAdminId()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getParameterKey() == null ? other.getParameterKey() == null : this.getParameterKey().equals(other.getParameterKey()))
            && (this.getParameterValue() == null ? other.getParameterValue() == null : this.getParameterValue().equals(other.getParameterValue()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateAdminId() == null) ? 0 : getCreateAdminId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastModifyAdminId() == null) ? 0 : getLastModifyAdminId().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getParameterKey() == null) ? 0 : getParameterKey().hashCode());
        result = prime * result + ((getParameterValue() == null) ? 0 : getParameterValue().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createAdminId=").append(createAdminId);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastModifyAdminId=").append(lastModifyAdminId);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", parameterKey=").append(parameterKey);
        sb.append(", parameterValue=").append(parameterValue);
        sb.append(", remark=").append(remark);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}