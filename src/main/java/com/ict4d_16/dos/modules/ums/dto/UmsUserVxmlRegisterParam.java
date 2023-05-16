package com.ict4d_16.dos.modules.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * User Register Parameters
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsUserVxmlRegisterParam {
    @NotEmpty
    @ApiModelProperty(value = "phone", required = true)
    private String phone;
    @NotEmpty
    @ApiModelProperty(value = "nickname", required = true)
    private String nickName;
    @NotEmpty
    @ApiModelProperty(value = "language", required = true)
    private String language;
}
