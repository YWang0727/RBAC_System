package com.yuewang.rbac.model.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

/**
 * @ClassName RoleParam
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/12 17:24
 **/
@Data
public class RoleParam {

    @NotNull(message = "Role ID is required", groups = UpdateResources.class)
    private Long id;

    @NotBlank(message = "Role name is required", groups = CreateRole.class)
    @Length(min = 4, max = 20, message = "Role name must be between 4-20 characters in length.", groups = CreateRole.class)    private String name;

    private Set<Long> permissionIds;

    public interface CreateRole {
    }

    public interface UpdateResources {
    }

}
