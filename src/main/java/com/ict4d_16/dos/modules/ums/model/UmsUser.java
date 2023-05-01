package com.ict4d_16.dos.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Supplier Info Table
 * </p>
 *
 * @author macro
 * @since 2023-05-01
 */
@Getter
@Setter
@TableName("ums_user")
@ApiModel(value = "UmsUser对象", description = "Supplier Info Table")
public class UmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("User ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty("User Name")
    private String userRealName;

    @ApiModelProperty("User Account Name")
    private String username;

    @ApiModelProperty("User Account Password")
    private String password;

    @ApiModelProperty("User  Phone Number")
    private String phoneNumber;

    @ApiModelProperty("User Statue: 0 for off, 1 for on")
    private Integer userStatus;

    @ApiModelProperty("User Info Last Modified Time")
    private Date modifiedTime;


}
