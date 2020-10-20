package com.lzlk.base.utils.http;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname 邻座旅客
 * @Description HttpServletResponse 工具类
 * @Date 2019/3/14 16:12
 * @Created by 湖南达联
 */
public class HttpServletResponseUtil {


    /**
     * 初始化 HttpServletResponse
     * @param response
     */
    public static void initHttpResponse(HttpServletResponse response){

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
       // response.setDateHeader("expries", -1);
       // response.setHeader("Cache-Control", "no-cache");
       // response.setHeader("Pragma", "cache");
      // response.setHeader("Cache-Control", "no-store");

    }


    public static void responseMsg(HttpServletResponse response,Object jsonString){
        initHttpResponse(response);
        PrintWriter writer = null;
        try {
            writer  = response.getWriter();
            writer.print(jsonString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
        }

    }
}
