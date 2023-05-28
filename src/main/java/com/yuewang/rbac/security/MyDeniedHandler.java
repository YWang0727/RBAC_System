package com.yuewang.rbac.security;

import com.yuewang.rbac.enums.ResultCode;
import com.yuewang.rbac.model.VO.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName MyDeniedHandler
 * @Description Return no permission info
 * @Author Yue Wang
 * @Date 2023/5/28 16:09
 **/
public class MyDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResultVO<String> resultVO = new ResultVO<>(ResultCode.FORBIDDEN, "No permission");
        out.write(resultVO.toString());
        out.flush();
        out.close();
    }
}
