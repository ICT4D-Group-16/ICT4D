package com.ict4d_16.dos.modules.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Modify username and password parameters
 * Created by macro on 2019/10/9.
 */
@Getter
@Setter
public class UpdateAdminPasswordParam {
    @NotEmpty
    @ApiModelProperty(value = "username", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "old password", required = true)
    private String oldPassword;
    @NotEmpty
    @ApiModelProperty(value = "new password", required = true)
    private String newPassword;
}
