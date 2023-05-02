package com.ict4d_16.dos.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Order detail table. Each order has N order detail table.
 * </p>
 *
 * @author macro
 * @since 2023-05-01
 */
@Getter
@Setter
@TableName("pms_order_detail")
@ApiModel(value = "PmsOrderDetail对象", description = "Order detail table. Each order has N order detail table.")
public class PmsOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Order Detail ID")
    @TableId(value = "order_detail_id", type = IdType.AUTO)
    private Long orderDetailId;

    @ApiModelProperty("Order ID")
    private Long orderId;

    @ApiModelProperty("Product ID")
    private Long productId;

    @ApiModelProperty("Product Price")
    private BigDecimal productPrice;

    @ApiModelProperty("Product Quantity")
    private Integer productQuantity;

    @ApiModelProperty("Product Total Price")
    private BigDecimal productTotalPrice;

    @ApiModelProperty("Order Detail Last Modified Time")
    private Date modifiedTime;


}
