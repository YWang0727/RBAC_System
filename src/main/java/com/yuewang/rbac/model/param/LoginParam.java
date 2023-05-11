package com.yuewang.rbac.model.param;

import com.yuewang.rbac.enums.ExceptionCode;
import lombok.Data;
//validation: throw exception directly after accepting params
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Data  //from lombok, omit all get()\set()\toString()
public class LoginParam {

    //check if is blank. @NotBlank:use with String
    @NotBlank(message = "Username required")
    @Length(min = 4, max = 12, message = "Username must be between 4-12 characters in length.")
    //read the annotation on the field when meet error
    @ExceptionCode(value = 100001, message = "Invalid username.")
    private String username;

    @NotBlank(message = "Password required")
    @Length(min = 4, max = 12, message = "Password must be between 4-12 characters in length.")
    @ExceptionCode(value = 100002, message = "Invalid password.")
    private String password;

}
