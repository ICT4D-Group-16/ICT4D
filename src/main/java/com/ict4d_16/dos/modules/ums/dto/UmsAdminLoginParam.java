package com.ict4d_16.dos.modules.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * User Login Parameters
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {
    @NotEmpty
    @ApiModelProperty(value = "username", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "password", required = true)
    private String password;
}
