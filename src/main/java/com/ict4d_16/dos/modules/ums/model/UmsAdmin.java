package com.ict4d_16.dos.modules.ums.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_admin")
@ApiModel(value = "UmsAdmin Object", description = "background user table")
public class UmsAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    @ApiModelProperty(value = "avatar")
    private String icon;

    @ApiModelProperty(value = "Phone Number")
    private String phone;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "Address")
    private String address;

    @ApiModelProperty(value = "nickname")
    private String nickName;

    @ApiModelProperty(value = "note")
    private String note;

    @ApiModelProperty(value = "create time")
    private Date createTime;

    @ApiModelProperty(value = "last login time")
    private Date loginTime;

    @ApiModelProperty(value = "status: 0->disable, 1->enable")
    private Integer status;

    @ApiModelProperty(value = "user preferred language")
    private String language;
}
