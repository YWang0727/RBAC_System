package com.yuewang.rbac.model.VO;


import com.yuewang.rbac.model.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @ClassName UserDetailsVO
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/9 18:33
 **/
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserDetailsVO extends org.springframework.security.core.userdetails.User {

    private User user;

    public UserDetailsVO(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

}
