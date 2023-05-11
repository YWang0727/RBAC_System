package com.yuewang.rbac.model.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @ClassName UserParam
 * @Description Receive user-related parameters.
 * @Author Yue Wang
 * @Date 2023/5/10 15:48
 **/
@Data
public class UserParam {

    @NotNull(message = "UserID is required.", groups = Update.class)
    private Long id;

    @NotBlank(message = "Username is required.", groups = CreateUser.class)
    @Length(min = 4, max = 12, message = "Username must be between 4 and 12 characters in length.", groups = CreateUser.class)
    private String username;

    private List<Long> roleIds;

//    private List<Long> companyIds;

    public interface Update {}

    public interface CreateUser{}

}
