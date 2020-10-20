package com.lzlk.base.utils.mobile;

public class MobileUtil {

    /**
     * 判断传入的参数号码为哪家运营商
     *
     * @param mobile
     * @return 运营商名称
     */
    public static String validateMobile(String mobile) {
        String returnString = "";
        if (mobile == null || mobile.trim().length() != 11) {
            return "未知非11位手机号";        //mobile参数为空或者手机号码长度不为11，错误！  
        }
        String pre = mobile.trim().substring(0, 3);
        String[] yidongStr = {"134", "135", "136", "137", "138", "139", "150",
                "151", "152", "157", "158", "159","165"
                , "182", "183", "184", "187", "188"
                , "147", "178", "184","198"};

        for (String yidong : yidongStr) {
            if (pre.equals(yidong)) {
                return "中国移动";//中国移动
            }
        }
        String[] liantongStr = {"130", "131", "132",
                "155", "156","166","171"
                , "185", "186","175"
                , "145", "176"};

        for (String liantong : liantongStr) {
            if (pre.equals(liantong)) {
                return "中国联通";//中国移动
            }
        }

        String[] dianxinStr = {"133",
                "153", "173", "177"
                , "180", "181", "189","191","199"};
        for (String dianxin : dianxinStr) {
            if (pre.equals(dianxin)) {
                return "中国电信";//中国移动
            }
        }
        if ("".equals(returnString.trim())) {
            returnString = "未知运营商 " + pre;   //未知运营商
        }
        return returnString;
    }


    /**
     * 手机号星号代替中间号码，前后留3位
     * @param phoneNum 用户手机号
     * @return  替换后的手机号
     */
    public static String validatePhoneNum(String phoneNum) {
        String phoneNumber = null;
        //1. 判断传进来的手机号为空或者手机号码长度不为11,都为未绑定
        if (phoneNum == null || phoneNum.length() != 11) {
            return phoneNum;
        }
        //通过正则检验,替换中间4位数,返回替换值
        phoneNumber = phoneNum.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");

        return phoneNumber;
    }

    /**
     * 判断手机号是否为11位数
     */
    public static  boolean isNotNumLength(String phoneNum){
        return phoneNum != null && phoneNum.length() == 11;
    }


}
