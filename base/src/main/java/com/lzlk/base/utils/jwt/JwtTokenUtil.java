package com.lzlk.base.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * json web token简单实现
 */
public class JwtTokenUtil {


    private  static  final Map<String, Object> HEADER_MAP =  new HashMap<String,Object>(){{
        put("alg", "HS256");
        put("typ", "JWT");
    }};//设置头部

    private  static  final  String BODY = "body";//内容属性名
    private String token;


    /**
     *  创建token
     * @param objJson
     * @param key
     * @return
     * @throws Exception
     */
    public static String createToken(String objJson,String key){
        String token = "";
    try{
      token = JWT.create()
            .withHeader(HEADER_MAP)//header
            .withClaim(BODY, objJson)//payload
            .sign(Algorithm.HMAC256(key));//加密
       }catch (Exception e){
        e.printStackTrace();
     }
        return token;
    }

    /**
     * 验证token是否有效，无效token返回异常
     * @param token
     * @param key
     * @return
     * @throws Exception
     */
    public static String  decryption(String token,String key) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key))
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        return  claims.get(BODY).asString();
    }


    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的签发时间
     */
    public static Date getIssuedAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getValByKey(String token,String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,expireTime后过期
     * @param username 用户名
     * @param time 过期时间s
     * @return 加密的token
     */
    public static String sign(String username,String key, String salt, long time) {
        try {
            Date date = new Date(System.currentTimeMillis()+time*1000);
            Algorithm algorithm = Algorithm.HMAC256(salt);
            // 附带username信息
            return JWT.create()
                    .withClaim(key, username)
                    .withExpiresAt(date)
                    .withIssuedAt(new Date())
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * token是否过期
     * @return true：过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }






    public static void main(String[] args) throws  Exception{
//        String str = JwtTokenUtil.decryption("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJib2R5IjoiMTMyNjY1MTE2NTA3MjY3MDcyLDEifQ.u_5gxYUAD6oNXTuZI8gg0iMS7Dlkmzw1AmLrsOzWFwE","jwt_token_secret_key");



    }

}
