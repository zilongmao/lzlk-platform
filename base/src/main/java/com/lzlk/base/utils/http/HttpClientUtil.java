package com.lzlk.base.utils.http;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 *
 * @Description
 * @date 2017年9月29日 下午3:23:57
 */
public class HttpClientUtil {


    public static String doGet(String url, Map<String, String> param) {
        return doGet(url, param, null);
    }


    public static String doGet(String url, Map<String, String> param, String authorization) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            if (StringUtils.isNotBlank(authorization)) {
                httpGet.setHeader("Authorization", authorization);
            }
            // 执行请求
            response = httpclient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return resultString;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求  
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表  
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求  
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        return doPostJson(url, null, json);
    }

    public static String doPostJson(String url, String authorization, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            if (StringUtils.isNotBlank(authorization)) {
                httpPost.setHeader("Authorization", authorization);
            }
            if (StringUtils.isNotBlank(json)) {
                StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return resultString;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String postXml(String url, String xmlInfo) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {

            // 创建Http Post请求
//            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(xmlInfo, "utf-8");
//            httpPost.setEntity(entity);

            HttpUriRequest httpUriRequest = RequestBuilder.post()
                    .setUri(new URI(url))
                    .setEntity(entity)
                    .setHeader("Content-Type", "application/xml").build();

            // 执行http请求
            response = httpclient.execute(httpUriRequest);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }


    /**
     * http post请求
     *
     * @param url         地址
     * @param postContent post内容格式为param1=value&param2=value2&param3=value3
     * @return
     * @throws IOException
     */
    public static String doPost(URL url, String postContent) throws Exception {
        OutputStream outputstream = null;
        BufferedReader in = null;
        try {
            URLConnection httpurlconnection = url.openConnection();
            httpurlconnection.setConnectTimeout(10 * 1000);
            httpurlconnection.setDoOutput(true);
            httpurlconnection.setUseCaches(false);
            OutputStreamWriter out = new OutputStreamWriter(httpurlconnection
                    .getOutputStream(), StandardCharsets.UTF_8);
            out.write(postContent);
            out.flush();
            StringBuffer result = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (outputstream != null) {
                try {
                    outputstream.close();
                } catch (IOException e) {
                    outputstream = null;
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    in = null;
                }
            }
        }
        return null;
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
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
}
