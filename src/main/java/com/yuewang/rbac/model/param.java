package com.yuewang.rbac.model;

import lombok.Data;
//validation: throw exception directly after accepting params
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Data  //from lombok, omit all get()\set()\toString()
public class param {

    @NotBlank(message = "用户名不能为空")  //check if is black. @NotBlank:use with String
    @Length(min = 4, max = 12, message = "用户名长度为4-12位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 12, message = "密码长度为4-12位")
    private String password;

}
