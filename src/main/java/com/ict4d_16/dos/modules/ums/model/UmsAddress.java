package com.ict4d_16.dos.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * User address table. One user can has multiple addresses.
 * </p>
 *
 * @author macro
 * @since 2023-05-01
 */
@Getter
@Setter
@TableName("ums_address")
@ApiModel(value = "UmsAddress对象", description = "User address table. One user can has multiple addresses.")
public class UmsAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Address ID")
    @TableId(value = "address_id", type = IdType.AUTO)
    private Long addressId;

    @ApiModelProperty("User ID")
    private Long userId;

    @ApiModelProperty("Name")
    private String consigneeName;

    @ApiModelProperty("Phone Number")
    private String phoneNumber;

    @ApiModelProperty("Address")
    private String address;

    @ApiModelProperty("Address Priority: 0 as first for default")
    private Integer priority;

    @ApiModelProperty("Address Deleted: 0 for normal, 1 for deleted")
    private Integer deleted;


}
