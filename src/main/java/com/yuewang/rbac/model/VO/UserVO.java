package com.yuewang.rbac.model.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
//@Accessors: from lombok, for generating chain method for attributes in the class
// and return the current object itself automatically
@Accessors(chain = true)
public class UserVO {  //VO(Value Object): encapsulated users' data\token\permission
    private Long id;

    private String username;

    private String token;  //Login authentication token

//    private Set<Role> roles;

    private Set<Long> permissionIds;  //set of permission ids of current user
}
