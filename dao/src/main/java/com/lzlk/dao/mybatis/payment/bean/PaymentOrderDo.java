package com.lzlk.dao.mybatis.payment.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * payment_order
 * @author 
 */
public class PaymentOrderDo implements Serializable {
    /**
     * 支付订单号
     */
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户订单号
     */
    private String merchantOrderNumber;

    /**
     * 渠道ID
     */
    private String channelId;

    /**
     * 支付金额,单位分
     */
    private Long amount;

    /**
     * 支付状态,0-订单生成,1-支付中(目前未使用),2-支付成功,3-业务处理完成
     */
    private Integer status;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 设备
     */
    private String device;

    /**
     * 商品标题
     */
    private String goodsName;

    /**
     * 商品描述信息
     */
    private String goodsInfo;

    /**
     * 特定渠道发起时额外参数
     */
    private String extra;

    /**
     * 渠道支付错误码
     */
    private String errCode;

    /**
     * 渠道支付错误描述
     */
    private String errMsg;

    /**
     * 通知次数
     */
    private Integer notifyCount;

    /**
     * 最后一次通知时间
     */
    private Date lastNotifyTime;

    /**
     * 订单失效时间
     */
    private Date expireTime;

    /**
     * 订单支付成功时间
     */
    private Date successTime;

    /**
     * 更新时间
     */
    private Date lastModifyTime;

    /**
     * 流水单号
     */
    private String transactionId;

    private String param2;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantOrderNumber() {
        return merchantOrderNumber;
    }

    public void setMerchantOrderNumber(String merchantOrderNumber) {
        this.merchantOrderNumber = merchantOrderNumber;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getNotifyCount() {
        return notifyCount;
    }

    public void setNotifyCount(Integer notifyCount) {
        this.notifyCount = notifyCount;
    }

    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
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
        PaymentOrderDo other = (PaymentOrderDo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getMerchantOrderNumber() == null ? other.getMerchantOrderNumber() == null : this.getMerchantOrderNumber().equals(other.getMerchantOrderNumber()))
            && (this.getChannelId() == null ? other.getChannelId() == null : this.getChannelId().equals(other.getChannelId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getClientIp() == null ? other.getClientIp() == null : this.getClientIp().equals(other.getClientIp()))
            && (this.getDevice() == null ? other.getDevice() == null : this.getDevice().equals(other.getDevice()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getGoodsInfo() == null ? other.getGoodsInfo() == null : this.getGoodsInfo().equals(other.getGoodsInfo()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getErrCode() == null ? other.getErrCode() == null : this.getErrCode().equals(other.getErrCode()))
            && (this.getErrMsg() == null ? other.getErrMsg() == null : this.getErrMsg().equals(other.getErrMsg()))
            && (this.getNotifyCount() == null ? other.getNotifyCount() == null : this.getNotifyCount().equals(other.getNotifyCount()))
            && (this.getLastNotifyTime() == null ? other.getLastNotifyTime() == null : this.getLastNotifyTime().equals(other.getLastNotifyTime()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getSuccessTime() == null ? other.getSuccessTime() == null : this.getSuccessTime().equals(other.getSuccessTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getTransactionId() == null ? other.getTransactionId() == null : this.getTransactionId().equals(other.getTransactionId()))
            && (this.getParam2() == null ? other.getParam2() == null : this.getParam2().equals(other.getParam2()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getMerchantOrderNumber() == null) ? 0 : getMerchantOrderNumber().hashCode());
        result = prime * result + ((getChannelId() == null) ? 0 : getChannelId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getClientIp() == null) ? 0 : getClientIp().hashCode());
        result = prime * result + ((getDevice() == null) ? 0 : getDevice().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getGoodsInfo() == null) ? 0 : getGoodsInfo().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getErrCode() == null) ? 0 : getErrCode().hashCode());
        result = prime * result + ((getErrMsg() == null) ? 0 : getErrMsg().hashCode());
        result = prime * result + ((getNotifyCount() == null) ? 0 : getNotifyCount().hashCode());
        result = prime * result + ((getLastNotifyTime() == null) ? 0 : getLastNotifyTime().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getSuccessTime() == null) ? 0 : getSuccessTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getTransactionId() == null) ? 0 : getTransactionId().hashCode());
        result = prime * result + ((getParam2() == null) ? 0 : getParam2().hashCode());
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
        sb.append(", merchantId=").append(merchantId);
        sb.append(", merchantOrderNumber=").append(merchantOrderNumber);
        sb.append(", channelId=").append(channelId);
        sb.append(", amount=").append(amount);
        sb.append(", status=").append(status);
        sb.append(", clientIp=").append(clientIp);
        sb.append(", device=").append(device);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsInfo=").append(goodsInfo);
        sb.append(", extra=").append(extra);
        sb.append(", errCode=").append(errCode);
        sb.append(", errMsg=").append(errMsg);
        sb.append(", notifyCount=").append(notifyCount);
        sb.append(", lastNotifyTime=").append(lastNotifyTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", successTime=").append(successTime);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", transactionId=").append(transactionId);
        sb.append(", param2=").append(param2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}