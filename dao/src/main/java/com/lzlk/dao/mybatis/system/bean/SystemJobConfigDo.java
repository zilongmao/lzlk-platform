package com.lzlk.dao.mybatis.system.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * system_job_config
 * @author 
 */
public class SystemJobConfigDo implements Serializable {
    /**
     * 主键，自增
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人（开发者）
     */
    private String createDeveloper;

    /**
     * 最后修改者
     */
    private String lastModifyDeveloper;

    /**
     * 任务名
     */
    private String jobName;

    /**
     * 运行状态，0关1开
     */
    private Integer status;

    /**
     * 任务备注
     */
    private String remark;

    /**
     * 控制开关，1关0开
     */
    private Boolean isDelete;

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

    public String getCreateDeveloper() {
        return createDeveloper;
    }

    public void setCreateDeveloper(String createDeveloper) {
        this.createDeveloper = createDeveloper;
    }

    public String getLastModifyDeveloper() {
        return lastModifyDeveloper;
    }

    public void setLastModifyDeveloper(String lastModifyDeveloper) {
        this.lastModifyDeveloper = lastModifyDeveloper;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        SystemJobConfigDo other = (SystemJobConfigDo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateDeveloper() == null ? other.getCreateDeveloper() == null : this.getCreateDeveloper().equals(other.getCreateDeveloper()))
            && (this.getLastModifyDeveloper() == null ? other.getLastModifyDeveloper() == null : this.getLastModifyDeveloper().equals(other.getLastModifyDeveloper()))
            && (this.getJobName() == null ? other.getJobName() == null : this.getJobName().equals(other.getJobName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateDeveloper() == null) ? 0 : getCreateDeveloper().hashCode());
        result = prime * result + ((getLastModifyDeveloper() == null) ? 0 : getLastModifyDeveloper().hashCode());
        result = prime * result + ((getJobName() == null) ? 0 : getJobName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", createTime=").append(createTime);
        sb.append(", createDeveloper=").append(createDeveloper);
        sb.append(", lastModifyDeveloper=").append(lastModifyDeveloper);
        sb.append(", jobName=").append(jobName);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}