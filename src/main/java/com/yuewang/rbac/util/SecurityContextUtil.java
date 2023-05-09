package com.yuewang.rbac.util;

import com.yuewang.rbac.model.VO.UserDetailsVO;
import com.yuewang.rbac.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName SecurityContextUtil
 * @Description get user info from spring security context (SecurityContextHolder - Authentication - Principal object)
 * @Author Yue Wang
 * @Date 2023/5/9 18:31
 **/
public class SecurityContextUtil {

    public static Long getCurrentUserId(){
        //SecurityContextHolder stores security-related information, provides access to the SecurityContext, which in turn contains the Authentication object.
        //Authentication represents the user's authentication status and contains details about the user, such as their username, password, and granted authorities.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //cast the Principal object within the Authentication object to a UserDetailsVO object
        UserDetailsVO userDetails = (UserDetailsVO)authentication.getPrincipal();
        return userDetails.getId();
    }

    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsVO userDetails = (UserDetailsVO)authentication.getPrincipal();
        return userDetails.getUser();
    }

}
