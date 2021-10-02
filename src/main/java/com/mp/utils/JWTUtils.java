package com.mp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT生成工具类
 */
@Slf4j
@Component
public class JWTUtils {
    /**请求头中的key*/
    public static String TOKEN_HEADER_KEY = "Authentication";
    /**token秘钥*/
    private static final String SECRET = "ho";
    /**加密算法*/
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    /**token有效期时间粒度*/
    public static final int calendarField = Calendar.DATE;
    /**token有效期*/
    public static final int calendarInterval = 10;

    /**
     * Token生成方法
     *
     * JWT构成 header payload signature
     * @param userName
     * @return token
     */
    public static String createToken(String userName){
        /*
         * header
         */
        HashMap<String, Object> header = new HashMap<>();
        header.put("alg","HS256");
        header.put("type","JWT");
        /*
        *token有效期设置
         */
        Date signDate = new Date();
        //获取当前时间
        Calendar nowTime = Calendar.getInstance();
        //有效期设置
        nowTime.add(calendarField,calendarInterval);
        Date expiresTime = nowTime.getTime();

        String Token = JWT.create()
                //header
                .withHeader(header)
                //payload
                .withClaim("userName",userName)
                //signature
                .withIssuedAt(signDate)//签发时间
                .withExpiresAt(expiresTime)//有效期至
                .sign(ALGORITHM);
        return Token;
    }

    /**
     * 验证Token的合法性有效性
     * @param token
     * @return
     */
    private static Map<String, Claim> verifyToken(String token){
        JWTVerifier verifier = JWT.require(ALGORITHM)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    /**
     * 验证token的有效性
     * 获取token Claim中的userName
     *  username为空抛异常
     * @param token
     * @return
     */
    public static String getUserNameFromToken(String token) throws RuntimeException{
        try{
            Map<String, Claim> claimMap = verifyToken(token);
            String userName = claimMap.get("userName").asString();
            if (ObjectUtils.isEmpty(claimMap) || StringUtils.isEmpty(userName)){
                throw new RuntimeException("Token illegal or expire");
            }
            return userName;
        }catch (Exception e){
            throw new RuntimeException("Token illegal or expire");
        }
    }
}
