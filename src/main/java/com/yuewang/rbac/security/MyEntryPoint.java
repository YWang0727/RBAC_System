package com.yuewang.rbac.security;

import com.yuewang.rbac.enums.ResultCode;
import com.yuewang.rbac.model.VO.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName MyEntryPoint
 * @Description Authentication exception handling
 * @Author Yue Wang
 * @Date 2023/5/27 21:56
 **/
@Slf4j
public class MyEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.error(e.getMessage());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResultVO<String> resultVO = new ResultVO<>(ResultCode.UNAUTHORIZED, "没有登录");
        out.write(resultVO.toString());
        out.flush();
        out.close();
    }
}
