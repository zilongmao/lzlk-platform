package com.lzlk.base.qq;


import com.alibaba.fastjson.JSONObject;
import com.lzlk.base.tencent.TencentUtils;

/**
 * @program:hndl-platform
 * @description:TODO qq工具类
 * @author: 邻座旅客
 * @create: 2019/9/29 10:59
 * @Created by湖南达联
 **/
public class QqUtils {

    static String GRANT_TYPE = "client_credential";

    public static String IMG_SEC_CHECK_URL = "https://api.q.qq.com/api/json/security/ImgSecCheck";

    private static  String TOKEN_URL = "https://api.q.qq.com/api/getToken";
    /**
     * 获取请求token
     *
     * @param appId
     * @param secret
     * @return
     */
    public static String getAccessToken(String appId, String secret) {
        return TencentUtils.getAccessToken(TOKEN_URL, GRANT_TYPE, appId, secret);
    }

    /**
     * imgSecCheck 图片检测
     *
     * @param imgUrl 需要检测图片的url
     * @param accessToken token值
     * @return
     * @throws Exception
     */
//    public static String checkImgByInputStream(String imgUrl, String accessToken) throws Exception {
//        return TencentUtils.checkImgByInputStream(IMG_SEC_CHECK_URL,imgUrl, accessToken);
//    }


    /**
     * 检测图片是否安全
     *
     * @param imgUrl
     * @param accessToken
     * @return
     */
//    public static boolean checkImg(String imgUrl, String accessToken) {
//        boolean flag = true;
//        try {
//            String s = checkImgByInputStream(imgUrl, accessToken);
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            //判断是否安全图qq官方文档写了errcode，其实返回的是errCode
//            flag = !("87014".equals(jsonObject.getString("errCode")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }
//
//    public static void main(String[] args) throws Exception{
//        //测试获取token
//        //System.out.println(getAccessToken("1109660069", "rnTtaoc2MtwHTofJ"));
//        //测试获取
//        System.out.println(checkImgByInputStream("http://mghd.oss-cn-hangzhou.aliyuncs.com/temp/min_img/2019-09-29/296539573067120640.jpg", "iely788ShVteqcKT2w1ArGzyVLoM64tBmAkxb3KiGApAOBVZdN1I45L1KSDcxAcDqM0R"));
//    }
}
