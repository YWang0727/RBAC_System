package com.yuewang.rbac.model.VO;

import lombok.Data;

import java.util.Set;

/**
 * @ClassName UserPageVO
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/13 22:33
 **/
@Data
public class UserPageVO {

    private Long id;
    private String username;
    private Set<Long> roleIds;

}
