package com.mp.common.response;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
@Slf4j
public class ResponseUtils {
    public static void transmitMsg(HttpServletResponse response,Object msg){
        PrintWriter writer = null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/javascript;charset=utf-8");
            writer = response.getWriter();
            writer.write(JSON.toJSONString(msg));
        }catch (Exception e){
            log.error("transmitMsg Exception->{}",e.getMessage());
        }finally {
            if(writer!=null){
                writer.flush();
                writer.close();
            }

        }
    }
}
