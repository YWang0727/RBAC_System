package com.yuewang.rbac.security;

import cn.hutool.core.util.StrUtil;
import com.yuewang.rbac.service.impl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @ClassName LoginFilter
 * @Description is a JWT authentication filter, used in Spring Security to process incoming requests:
 * The main purpose of this filter is to extract the JWT Token from the HTTP header and extract the user's authentication info from it.
 * Based on this info, the user details, password, role info etc. are then retrieved
 * and a UserDetailsVO object containing the user details is generated and set to the SecurityContext of the current thread using the UsernamePasswordAuthenticationToken,
 * indicating that the user has been authenticated and has the appropriate authorisation info.
 * The user is authenticated and has the appropriate authorization info.
 * @Author Yue Wang
 * @Date 2023/5/27 15:55
 **/

@Slf4j
@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // get JWT token from HTTP header
        // if token is null or is not started with 'Bearer ', skip this filter
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        // get username from JWT, and query database
        // verify if token is valid while get username
        username = JwtManager.extractUsername(jwt);

        // if username is valid, and no users configured in the SecurityContextHolder
        if (StrUtil.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            // get user info\password\role...from database, return an UserDetailsVO with user details
            UserDetails userDetails = userService.loadUserByUsername(username);
            // create an object which includes user's authentication info, credential info(authenticated JWT, no credentials required), user's authorization info
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            // encapsulate the object of HTTP request's detail, includes request's IP\Session ID\User Agent...
            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            // Set the authToken to the SecurityContext of the current thread to indicate that the user has been authenticated and has the appropriate authorization info
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
