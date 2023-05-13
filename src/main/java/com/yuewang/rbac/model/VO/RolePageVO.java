package com.yuewang.rbac.model.VO;

import lombok.Data;

import java.util.Set;

/**
 * @ClassName RolePageVO
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/13 18:33
 **/
@Data
public class RolePageVO {

    private Long id;
    private String name;
    private Set<Long> permissionIds;

}
