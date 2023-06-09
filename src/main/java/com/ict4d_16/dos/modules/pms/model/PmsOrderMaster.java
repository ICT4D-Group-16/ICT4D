package com.ict4d_16.dos.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Order Info Table
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
@Getter
@Setter
@TableName("pms_order_master")
@ApiModel(value = "PmsOrderMaster Object", description = "Order Info Table")
public class PmsOrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Order ID")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty("Customer User ID")
    private Long userId;

    @ApiModelProperty("Order Money, US dollar. Order Money = All Producats Money + Shipping Money")
    private BigDecimal orderMoney;

    @ApiModelProperty("Shipping Money")
    private BigDecimal shippingMoney;

    @ApiModelProperty("Shipping Tracking Number")
    private String trackingNumber;

    @ApiModelProperty("Order Create Time")
    private Date createTime;

    @ApiModelProperty("Shipping Time")
    private Date shippingTime;

    @ApiModelProperty("Pay Time")
    private Date payTime;

    @ApiModelProperty("Receive Time")
    private Date receiveTime;

    @ApiModelProperty("Order Status: 0 for created, 1 for paid, 2 for completed, 3 for cancelled")
    private Integer orderStatus;

    @ApiModelProperty("Order Info Last Modified Time")
    private Date modifiedTime;

    @TableField(exist = false)
    private List<PmsOrderDetail> orderDetails;
}
