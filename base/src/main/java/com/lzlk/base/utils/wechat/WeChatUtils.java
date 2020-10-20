package com.lzlk.base.utils.wechat;

import com.alibaba.fastjson.JSONObject;
import com.lzlk.base.tencent.TencentUtils;
import com.lzlk.base.utils.http.HttpClientUtil;
import com.lzlk.base.tencent.TencentUtils;
import com.lzlk.base.utils.http.HttpClientUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author 邻座旅客
 * @Description 微信工具类
 * @Date 2019/8/26 11:47
 * @Created by 湖南达联
 */
public class WeChatUtils {

    private static String GRANT_TYPE = "client_credential";

    private static String IMG_SEC_CHECK_URL = "https://api.weixin.qq.com/wxa/img_sec_check";

    private static  String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    private static final String USER_NAME = "屏霸_";

    private static final Integer USER_NAME_LENGTH = 8;

    /**
     * 获取微信AccessToken
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getWeChatAccessToken(String appId, String appSecret) {
        return TencentUtils.getAccessToken(TOKEN_URL, GRANT_TYPE, appId, appSecret);
    }

    /**
     * 获取微信AccessTokenJosn
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public static JSONObject getWeChatAccessTokenJson(String appId, String appSecret) {
        return TencentUtils.getAccessTokenJson(TOKEN_URL, GRANT_TYPE, appId, appSecret);
    }

    /**
     * 获取用户Oauth2 AccessToken
     *
     * @param appId
     * @param appSecret
     * @param code
     * @return
     */
    public static String getOauth2WechatAccess(String appId, String appSecret, String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId +
                "&secret=" + appSecret +
                "&code=" + code +
                "&grant_type=authorization_code";

        return HttpClientUtil.doGet(url);
    }


    /**
     * 获取用户信息(小程序)
     *
     * @param token
     * @param openId
     * @return
     */
    public static String getUserInfoApplet(String token, String openId) {
        //获取信息
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token +
                "&openid=" + openId +
                "&lang=zh_CN";

        return HttpClientUtil.doGet(infoUrl);
    }

    /**
     * 获取用户信息(公众号)
     *
     * @param token
     * @param openId
     * @return
     */
    public static String getUserInfoOfficialAccount(String token, String openId) {
        //获取信息
        String infoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token +
                "&openid=" + openId +
                "&lang=zh_CN";
        return HttpClientUtil.doGet(infoUrl);
    }

    /**
     * 获取跳转地址
     *
     * @param appId
     * @param redirectUrl
     * @return
     */
    public static String oauth2buildAuthorizationUrl(String appId, String redirectUrl) {
        try {
            return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId +
                    "&redirect_uri=" + URLEncoder.encode(redirectUrl, "UTF-8") +
                    "&response_type=code" +
                    "&scope=snsapi_userinfo" +
                    "&state=STATE#wechat_redirect";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送消息
     *
     * @param token
     * @param jsonContent
     * @return
     */
    public static String sendWeChatMsg(String token, String jsonContent) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send" + "?access_token=" + token;
        return HttpClientUtil.doPostJson(url, jsonContent);
    }

    /**
     * 接收request对象，读取xml内容，转换成map对象
     *
     * @param request
     * @return
     */
    public static Map<String, String> parseXmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream ins = null;
        try {
            ins = request.getInputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Document doc = null;
        try {
            doc = reader.read(ins);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
            return map;
        } catch (DocumentException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (null != ins) {
                    ins.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将消息转换成xml格式的字符串
     *
     * @param msg   各种信息类，如文本信息类，图片信息类，音频信息类等。（都是WxMessage的子类）
     * @param child 用来确定到底是哪一种子类
     * @return
     */
    public static String parseMsgToXml(WxMessage msg, Class child) {
        xstream.alias("xml", child);
        return xstream.toXML(msg);
    }

    /**
     * 定义xstream让value自动加上CDATA标签
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                boolean cdata = false;

                @Override
                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    if (!name.equals("xml")) {
                        char[] arr = name.toCharArray();
                        if (arr[0] >= 'a' && arr[0] <= 'z') {
                            // arr[0] -= 'a' - 'A';
                            arr[0] = (char) ((int) arr[0] - 32);
                        }
                        name = new String(arr);
                    }
                    super.startNode(name, clazz);
                }

                @Override
                public void setValue(String text) {
                    if (text != null && !"".equals(text)) {
                        Pattern patternInt =compile("[0-9]*(\\.?)[0-9]*");
                        Pattern patternFloat = compile("[0-9]+");
                        cdata = !patternInt.matcher(text).matches()
                                && !patternFloat.matcher(text).matches();
                    }
                    super.setValue(text);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        String accessToken = getWeChatAccessToken("wx4b09491c12b811cd", "f8e627532fc1cdfc7108ee00ffa1c47b");
        System.out.println(accessToken);
     //   System.out.println(checkImgByInputStream("http://mghd.oss-cn-hangzhou.aliyuncs.com/temp/min_img/2019-09-29/296539573067120640.jpg", accessToken));
    }

    /**
     * imgSecCheck 图片检测
     *
     * @param imgUrl
     * @return
     * @throws Exception
     */
//    public static String checkImgByInputStream(String imgUrl, String accessToken) throws Exception {
//        return TencentUtils.checkImgByInputStream(IMG_SEC_CHECK_URL, imgUrl, accessToken);
//    }


    /**
     * msgSecCheck 文字检测
     *
     * @param content
     * @param accessToken
     * @return
     */
    public static String checkMsg(String content, String accessToken) {
        String msgSecCheckUrl = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=%s";
        String url = String.format(msgSecCheckUrl, accessToken);
        Map<String, String> map = new HashMap<>();
        map.put("content", content);
        String result = HttpClientUtil.doPostJson(url, JSONObject.toJSONString(map));
        return result;
    }

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
//            //判断是否安全图
//            flag = !("87014".equals(jsonObject.getString("errcode")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }

    /**
     * 监测是否安全的文字【调用微信】
     *
     * @param content
     * @param accessToken
     * @return
     */
    public static boolean checkContent(String content, String accessToken) {
        boolean flag = true;
        try {
            String s = checkMsg(content, accessToken);
            JSONObject jsonObject = JSONObject.parseObject(s);
            //判断是否安全的文字
            flag = !("87014".equals(jsonObject.getString("errcode")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 获取网络图片流
     *
     * @param url
     * @return
     */
//    public static InputStream getImageStream(String url, long num) throws IOException {
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
