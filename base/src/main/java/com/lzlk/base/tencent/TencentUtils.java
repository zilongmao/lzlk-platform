package com.lzlk.base.tencent;

import com.alibaba.fastjson.JSONObject;
import com.lzlk.base.utils.http.HttpClientUtil;
import com.lzlk.base.utils.http.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @program:hndl-platform
 * @description:TODO 腾讯系公用操作类
 * @author: 邻座旅客
 * @create: 2019/9/29 11:15
 * @Created by湖南达联
 **/
public class TencentUtils {

    static String REQ_TOKEN_PARAMS = "?grant_type=%s&appid=%s&secret=%s";

    static String SEC_CHECK_PARAMS = "?access_token=%s";
    /**
     * 获取腾讯系的token
     * @param reqUrl 请求url
     *               exp:https://api.q.qq.com/api/getToken
     * @param grantType
     * @param appid
     * @param secret
     * @return
     */
    public static  String getAccessToken(String reqUrl,String grantType,String appid,String secret){
        JSONObject jsonObject = getAccessTokenJson(reqUrl, grantType, appid, secret);
        return jsonObject.getString("access_token");
    }

    /**
     * 获取微信token
     * @param reqUrl 请求地址
     * @param grantType 类型
     * @param appid
     * @param secret
     * @return
     */
    public  static JSONObject getAccessTokenJson(String reqUrl, String grantType, String appid, String secret){
        if (StringUtils.isBlank(reqUrl)) {
            throw new IllegalArgumentException("the reqUrl must not to be null ");
        }
        String url = String.format(reqUrl+REQ_TOKEN_PARAMS, grantType, appid, secret);
        String result = HttpClientUtil.doGet(url);
       return JSONObject.parseObject(result);
    }

//    /**
//     * imgSecCheck 图片检测
//     * @param reqUrl 请求url
//     *               exp:https://api.weixin.qq.com/wxa/img_sec_check
//     * @param imgUrl 需要校验的图片的url
//     * @param accessToken
//     * @return
//     * @throws Exception
//     */
//    public static String checkImgByInputStream(String reqUrl,String imgUrl, String accessToken) throws Exception {
//
//
//        //进行图片压缩
//        InputStream inputStream = getImageStream(imgUrl, 1024);
//        if (inputStream == null) {
//            return "";
//        }
//        String requestUrl = String.format(reqUrl+SEC_CHECK_PARAMS, accessToken);
//        //判断是qq
//        if (QqUtils.IMG_SEC_CHECK_URL.equals(reqUrl)){
//            Map<String, ContentBody> reqParam = new HashMap<>();
//            reqParam.put("media",new InputStreamBody(inputStream,"img.jpg"));
//            return HttpClientUtil.doPostFileMultiPart(requestUrl, reqParam);
//        }
//
//        //其他走微信
//
//        HttpClient httpclient = HttpClients.createDefault();
//
//        HttpPost request = new HttpPost(requestUrl);
//        request.addHeader("Content-Type", "application/octet-stream");
//        try {
//            byte[] byt = new byte[inputStream.available()];
//            inputStream.read(byt);
//            request.setEntity(new ByteArrayEntity(byt, ContentType.create("image/jpg")));
//            HttpResponse response = httpclient.execute(request);
//            HttpEntity entity = response.getEntity();
//            // 转成string
//            String result = EntityUtils.toString(entity, "UTF-8");
//            return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return "";
//    }



    /**
     * 获取网络图片流
     *
     * @param url
     * @return
     */
//    private static InputStream getImageStream(String url, long num) throws IOException {
//        if (num == 0 || StringUtils.isBlank(url)) {
//            return null;
//        }
//        URLConnection connection = new URL(url).openConnection();
//        double multiple = 1.0 / (num / (1024 * 1024) + 1.0);
//        BufferedImage image = Thumbnails.of(connection.getInputStream()).scale(multiple).asBufferedImage();
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        ImageIO.write(image, "jpg", os);
//        InputStream is = new ByteArrayInputStream(os.toByteArray());
//        return is;
//    }
}
