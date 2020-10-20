package com.lzlk.dao.mybatis.user.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * user_info
 * @author 
 */
public class UserInfoDo implements Serializable {
    /**
     * 用户唯一标识
     */
    private Long id;

    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    private Boolean isDelete;

    /**
     * 用户昵称 唯一
     */
    private String nickname;

    /**
     * 公众号openId
     */
    private String officialAccountOpenId;

    /**
     * 小程序openId
     */
    private String appletOpenId;

    /**
     * 微信unionID，用户唯一标识
     */
    private String weChatUnionId;

    /**
     * 性别，0女1男
     */
    private Integer sex;

    /**
     * 用户头像路径 - 大
     */
    private String headImageMax;

    /**
     * 用户头像路径 - 小
     */
    private String headImageMin;

    /**
     * 手机号码
     */
    private Long phoneNumber;

    private Integer other1;

    private Integer other2;

    private String other3;

    private String other4;

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

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOfficialAccountOpenId() {
        return officialAccountOpenId;
    }

    public void setOfficialAccountOpenId(String officialAccountOpenId) {
        this.officialAccountOpenId = officialAccountOpenId;
    }

    public String getAppletOpenId() {
        return appletOpenId;
    }

    public void setAppletOpenId(String appletOpenId) {
        this.appletOpenId = appletOpenId;
    }

    public String getWeChatUnionId() {
        return weChatUnionId;
    }

    public void setWeChatUnionId(String weChatUnionId) {
        this.weChatUnionId = weChatUnionId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadImageMax() {
        return headImageMax;
    }

    public void setHeadImageMax(String headImageMax) {
        this.headImageMax = headImageMax;
    }

    public String getHeadImageMin() {
        return headImageMin;
    }

    public void setHeadImageMin(String headImageMin) {
        this.headImageMin = headImageMin;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getOther1() {
        return other1;
    }

    public void setOther1(Integer other1) {
        this.other1 = other1;
    }

    public Integer getOther2() {
        return other2;
    }

    public void setOther2(Integer other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }

    public String getOther4() {
        return other4;
    }

    public void setOther4(String other4) {
        this.other4 = other4;
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
        UserInfoDo other = (UserInfoDo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getOfficialAccountOpenId() == null ? other.getOfficialAccountOpenId() == null : this.getOfficialAccountOpenId().equals(other.getOfficialAccountOpenId()))
            && (this.getAppletOpenId() == null ? other.getAppletOpenId() == null : this.getAppletOpenId().equals(other.getAppletOpenId()))
            && (this.getWeChatUnionId() == null ? other.getWeChatUnionId() == null : this.getWeChatUnionId().equals(other.getWeChatUnionId()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getHeadImageMax() == null ? other.getHeadImageMax() == null : this.getHeadImageMax().equals(other.getHeadImageMax()))
            && (this.getHeadImageMin() == null ? other.getHeadImageMin() == null : this.getHeadImageMin().equals(other.getHeadImageMin()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getOther1() == null ? other.getOther1() == null : this.getOther1().equals(other.getOther1()))
            && (this.getOther2() == null ? other.getOther2() == null : this.getOther2().equals(other.getOther2()))
            && (this.getOther3() == null ? other.getOther3() == null : this.getOther3().equals(other.getOther3()))
            && (this.getOther4() == null ? other.getOther4() == null : this.getOther4().equals(other.getOther4()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getOfficialAccountOpenId() == null) ? 0 : getOfficialAccountOpenId().hashCode());
        result = prime * result + ((getAppletOpenId() == null) ? 0 : getAppletOpenId().hashCode());
        result = prime * result + ((getWeChatUnionId() == null) ? 0 : getWeChatUnionId().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getHeadImageMax() == null) ? 0 : getHeadImageMax().hashCode());
        result = prime * result + ((getHeadImageMin() == null) ? 0 : getHeadImageMin().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getOther1() == null) ? 0 : getOther1().hashCode());
        result = prime * result + ((getOther2() == null) ? 0 : getOther2().hashCode());
        result = prime * result + ((getOther3() == null) ? 0 : getOther3().hashCode());
        result = prime * result + ((getOther4() == null) ? 0 : getOther4().hashCode());
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
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", nickname=").append(nickname);
        sb.append(", officialAccountOpenId=").append(officialAccountOpenId);
        sb.append(", appletOpenId=").append(appletOpenId);
        sb.append(", weChatUnionId=").append(weChatUnionId);
        sb.append(", sex=").append(sex);
        sb.append(", headImageMax=").append(headImageMax);
        sb.append(", headImageMin=").append(headImageMin);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", other1=").append(other1);
        sb.append(", other2=").append(other2);
        sb.append(", other3=").append(other3);
        sb.append(", other4=").append(other4);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}