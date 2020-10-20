package com.lzlk.main.business.pay.util;

import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.utils.http.HttpClientUtil;
import com.lzlk.base.utils.request.RequestUtil;
import com.lzlk.base.utils.sign.SginUtils;
import com.lzlk.dao.mybatis.payment.bean.PaymentMerchantInfoDo;
import com.lzlk.main.business.pay.dto.OfficialAccountPayDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;


/**
 * 微信支付Utils
 *
 * @author 邻座旅客
 * @date 2020年4月6日 16:48:34
 */
@Slf4j
public class WeChatPayUtil {

    public static Map<String, String> getPreIdByApplets(OfficialAccountPayDto officialAccountPayDto,
                                                                HttpServletRequest request, PaymentMerchantInfoDo mch,
                                                                String notifyUrl, String payUrl, String sign, String appId) {
        Map<String, String> reqMap = new HashMap<String, String>();

        /* ------ 非必填项 ------ */
        /** 附加数据，在查询API和支付通知中原样返回 */
        reqMap.put("attach", officialAccountPayDto.getUserId().toString());
        /** 商品详情 */
        reqMap.put("detail", BaseConstants.WE_CHAT_PAY_DETAIL);
        /** 设备信息 */
        reqMap.put("device_info", RequestUtil.getMobileDevice(request));

        /* ------ 必填项 ------ */
        /** 用户对应公众号openID */
        reqMap.put("openid", officialAccountPayDto.getOpenId());
        /** 公众账号ID */
        reqMap.put("appid", appId);
        /** 商户ID */
        reqMap.put("mch_id", mch.getId());
        /** 随机字符串 */
        reqMap.put("nonce_str", getRandomString());
        /** 商品描述 */
        reqMap.put("body", BaseConstants.WE_CHAT_PAY_DETAIL);
        /** 系统内部的订单号(由开发人员生成) */
        reqMap.put("out_trade_no", officialAccountPayDto.getLocalOrderId());
        /** 订单金额，单位为分 */
        reqMap.put("total_fee", officialAccountPayDto.getAmount().toString());
        /** 下单用户IP */
        reqMap.put("spbill_create_ip", RequestUtil.getIPAddress(request));
        /** 支付结果回调地址  */
        reqMap.put("notify_url", notifyUrl);
        /** 交易类型 */
        reqMap.put("trade_type", "JSAPI");
        /** 签名 */
        reqMap.put("sign", SginUtils.getSign(reqMap, sign));

        String reqStr = createXml(reqMap);
        String retStr = HttpClientUtil.postXml(payUrl, reqStr);
        Map<String, String> infoByXml = getInfoByXml(retStr, sign);
        String preId = infoByXml.get("prepay_id");
        String nonceStr = infoByXml.get("nonce_str");
        String timeStamp = getTenTimes();

        Map<String, String> signMap = new HashedMap();
        signMap.put("appId", appId);
        signMap.put("nonceStr", nonceStr);
        signMap.put("timeStamp", timeStamp);
        signMap.put("package", "prepay_id=" + preId);
        signMap.put("signType", "MD5");


        Map<String, String> result = new HashMap<>();
        result.put("noncestr", nonceStr);
        result.put("preId", preId);
        result.put("partnerid", mch.getId());
        result.put("timestamp", timeStamp);
        result.put("package", "Sign=WXPay");
        result.put("sign", SginUtils.getSign(signMap, sign));

        return result;
    }

    /**
     * 传入map  生成头为XML的xml字符串，例：<xml><key>123</key></xml>
     *
     * @param reqMap
     * @return
     */
    public static String createXml(Map<String, String> reqMap) {
        Set<String> set = reqMap.keySet();
        StringBuffer stringBuffer = new StringBuffer("<xml>");
        for (String key : set) {
            if (key.equals("sign")) {
                stringBuffer.append("<").append(key).append(">");
                stringBuffer.append(reqMap.get(key));
                stringBuffer.append("</").append(key).append(">");
            } else {
                stringBuffer.append("<").append(key).append(">");
                stringBuffer.append("<![CDATA[").append(reqMap.get(key)).append("]]>");
                stringBuffer.append("</").append(key).append(">");
            }
        }
        stringBuffer.append("</xml>");
        return stringBuffer.toString();
    }

    /**
     * 得到10 位的时间戳
     * 如果在JAVA上转换为时间要在后面补上三个0
     *
     * @return
     */
    public static String getTenTimes() {
        String t = System.currentTimeMillis() + "";
        t = t.substring(0, t.length() - 3);
        return t;
    }

    /**
     * 得到随机字符串
     *
     * @return
     */
    public static String getRandomString() {
        int length = 32;
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);//[0,62)
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 得到本地机器的IP
     *
     * @return
     */
    public static String getHostIp() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 将XML转换为Map 验证加密算法 然后返回
     *
     * @param xml
     * @return
     */
    public static Map<String, String> getInfoByXml(String xml, String appSecret) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                String name = element.getName();
                String text = element.getText();
                map.put(name, text);
            }
            String retSign = map.get("sign");
            map.remove("sign");
            String rightSing = SginUtils.getSign(map, appSecret);
            if (rightSing.equals(retSign)) {
                return map;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    /**
     * 将金额转换成分
     *
     * @param fee 元格式的
     * @return 分
     */
    public static String changeToFen(Double fee) {
        String priceStr = "";
        if (fee != null) {
            int p = (int) (fee * 100); //价格变为分
            priceStr = Integer.toString(p);
        }
        return priceStr;
    }


//   public static void main(String[] args) {
//	   getPreyId("100004" ,"20180126112056123345213","600",
//			   "https://api.mch.weixin.qq.com/pay/unifiedorder",
//			   "http://shop.mgtvhd.com/shopM/ShopOrderInfoPay_weixinNotify.do",
//			   "25521909b894a0d0127d8db6ca4f9dfb","wxadec0d21568ce3dc",
//			   "1497384632");
//   }

}