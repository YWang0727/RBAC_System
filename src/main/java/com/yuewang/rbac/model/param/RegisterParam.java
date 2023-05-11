package com.yuewang.rbac.model.param;

import com.yuewang.rbac.enums.ExceptionCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName RegisterParam
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/10 11:26
 **/
@Data
public class RegisterParam {


    @NotBlank(message = "Phone number is required.")
    @Length(min = 8, max = 20, message = "Phone number must be between 8-20 digits in length.")
    @ExceptionCode(value = 100004, message = "Invalid phone number.")
    private String phone;

    @NotBlank(message = "Password is required.")
    @Length(min = 4, max = 20, message = "Password must be between 4-20 characters in length.")
    @ExceptionCode(value = 100003, message = "Invalid password.")
    private String password;

    @NotBlank(message = "Verification code is required.")
    @Pattern(regexp = "\\d{4}", message = "Verification code must be 4 digits.")
    @ExceptionCode(value = 100005, message = "Wrong code.")
    private String code;


}
