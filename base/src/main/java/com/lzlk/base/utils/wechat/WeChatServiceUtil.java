package com.lzlk.base.utils.wechat;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import static com.lzlk.base.utils.wechat.MediaUtil.judgeType;


/**
 * @author 邻座旅客
 * @Description TODO 微信公众号素材工具
 * @Date 2019/12/14 16:59
 * @Created by 湖南达联
 */
public class WeChatServiceUtil {

    /**
     * 上传临时文件消息(有效期 3天内)
     * http请求方式：POST/FORM
     */
    public static final String UPLOAD_TEMPORARY_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload?";

    /**
     * 上传永久素材(视频、语音、图片)
     */
    public static final String UPLOAD_PERMANENT_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/add_material?";

    /**
     * 获取永久素材，获取格式 图文和视频为消息素材，其他类型为素材的内容
     */
    private static final String GET_PERMANENT_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/get_material?";

    /**
     * 删除永久素材
     */
    private static final String DEL_PERMANENT_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/del_material?";


    /**
     * 获取素材列表
     */
    private static final String PERMANENT_MEDIA_LIST = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?";


    /**
     * @param path         请求url（可随意上传素材区分临时还是永久）
     * @param accessToken
     * @param type
     * @param mediaFileUrl
     * @return 上传多媒体数据到公众号服务器
     */
    public static String uploadWeChatServiceMaterial(String path, String accessToken, String type, String mediaFileUrl) {
        StringBuffer resultStr = null;
        //拼装url地址
        String mediaStr = path + "access_token=" + accessToken + "&type=" + type;
        URL mediaUrl;
        try {
            String boundary = "----WebKitFormBoundaryOYXo8heIv9pgpGjT";
            URL url = new URL(mediaStr);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            //让输入输出流开启
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            //使用post方式请求的时候必须关闭缓存
            urlConn.setUseCaches(false);
            //设置请求头的Content-Type属性
            urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            urlConn.setRequestMethod("POST");
            //获取输出流，使用输出流拼接请求体
            OutputStream out = urlConn.getOutputStream();

            //读取文件的数据,构建一个GET请求，然后读取指定地址中的数据
            mediaUrl = new URL(mediaFileUrl);
            HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl.openConnection();
            //设置请求方式
            mediaConn.setRequestMethod("GET");
            //设置可以打开输入流
            mediaConn.setDoInput(true);
            //获取传输的数据类型
            String contentType = mediaConn.getHeaderField("Content-Type");
            //将获取大到的类型转换成扩展名
            String fileExt = MediaUtil.judgeType(contentType);
            //获取输入流，从mediaURL里面读取数据
            InputStream in = mediaConn.getInputStream();
            BufferedInputStream bufferedIn = new BufferedInputStream(in);
            //数据读取到这个数组里面
            byte[] bytes = new byte[1024];
            int size = 0;
            //使用outputStream流输出信息到请求体当中去
            out.write(("--" + boundary + "\r\n").getBytes());
            out.write(("Content-Disposition: form-data; name=\"media\";\r\n"
                    + "filename=\"" + (new Date().getTime()) + fileExt + "\"\r\n"
                    + "Content-Type: " + contentType + "\r\n\r\n").getBytes());
            while ((size = bufferedIn.read(bytes)) != -1) {
                out.write(bytes, 0, size);
            }
            //切记，这里的换行符不能少，否则将会报41005错误
            out.write(("\r\n--" + boundary + "--\r\n").getBytes());

            bufferedIn.close();
            in.close();
            mediaConn.disconnect();

            InputStream resultIn = urlConn.getInputStream();
            InputStreamReader reader = new InputStreamReader(resultIn);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String tempStr = null;
            resultStr = new StringBuffer();
            while ((tempStr = bufferedReader.readLine()) != null) {
                resultStr.append(tempStr);
            }
            bufferedReader.close();
            reader.close();
            resultIn.close();
            urlConn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStr.toString();
    }


    /**
     * @param accessToken
     * @param mediaId     素材id
     * @return 获取永久文件消息 (包含图文和视频有返回json,其他类型默认返回素材内容)
     */
    public static String getWeChatServiceMaterialByMediaId(String accessToken, String mediaId) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String mediaStr = GET_PERMANENT_MEDIA + "access_token=" + accessToken;
        try {
            String boundary = "----WebKitFormBoundaryOYXo8heIv9pgpGjT";
            URL realUrl = new URL(mediaStr);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            //让输入输出流开启
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //使用post方式请求的时候必须关闭缓存
            conn.setUseCaches(false);
            //设置请求头的Content-Type属性
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print("{\"media_id\" : \"" + mediaId + "\"}");
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
           // System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * @param accessToken
     * @param mediaId     素材id
     * @return 删除永久素材（不能上传临时素材，临时素材有有效期）
     */
    public static String delWeChatServiceMaterialByMediaId(String accessToken, String mediaId) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String mediaStr = DEL_PERMANENT_MEDIA + "access_token=" + accessToken;
        try {
            String boundary = "----WebKitFormBoundaryOYXo8heIv9pgpGjT";
            URL realUrl = new URL(mediaStr);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            //让输入输出流开启
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //使用post方式请求的时候必须关闭缓存
            conn.setUseCaches(false);
            //设置请求头的Content-Type属性
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print("{\"media_id\" : \"" + mediaId + "\"}");
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
          //  System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param accessToken
     * @param mediaJSON   {"type":TYPE, "offset":OFFSET, "count":COUNT }
     * @return 获取永久素材列表
     */
    public static String findWeChatServiceMaterialByType(String accessToken, JSONObject mediaJSON) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String mediaStr = PERMANENT_MEDIA_LIST + "access_token=" + accessToken;
        try {
            String boundary = "----WebKitFormBoundaryOYXo8heIv9pgpGjT";
            URL realUrl = new URL(mediaStr);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            //让输入输出流开启
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //使用post方式请求的时候必须关闭缓存
            conn.setUseCaches(false);
            //设置请求头的Content-Type属性
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(mediaJSON);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
           // System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static void main(String[] args) {
        String accessToken = WeChatUtils.getWeChatAccessToken("wx4b09491c12b811cd", "f8e627532fc1cdfc7108ee00ffa1c47b");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "image");
        jsonObject.put("offset", "1");
        jsonObject.put("count", "20");
        String permanentMedia = findWeChatServiceMaterialByType(accessToken, jsonObject);
        System.out.println(permanentMedia);
    }
}
