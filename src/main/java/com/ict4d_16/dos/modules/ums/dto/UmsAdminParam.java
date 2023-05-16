package com.ict4d_16.dos.modules.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * User Login Parameters
 * Created by macro on 2018/4/26.
 */
@Getter
@Setter
public class UmsAdminParam {
    @NotEmpty
    @ApiModelProperty(value = "username", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "password", required = true)
    private String password;
    @ApiModelProperty(value = "user icon")
    private String icon;
    @NotEmpty
    @ApiModelProperty(value = "Phone Number", required = true)
    private String phone;
    @Email
    @ApiModelProperty(value = "email")
    private String email;
    @ApiModelProperty(value = "Address")
    private String address;
    @ApiModelProperty(value = "user nickname")
    private String nickName;
    @ApiModelProperty(value = "note")
    private String note;
    @ApiModelProperty(value = "user language")
    private String language;
}
