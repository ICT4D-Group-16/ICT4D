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
 * Product Info Table
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
@Getter
@Setter
@TableName("pms_product")
@ApiModel(value = "PmsProduct Object", description = "Product Info Table")
public class PmsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Product ID")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    @ApiModelProperty("Product Name")
    private String productName;

    @ApiModelProperty("Category ID")
    private Integer categoryId;

    @ApiModelProperty("Supplier User ID")
    private Integer supplierUserId;

    @ApiModelProperty("Product Price, US Dollar")
    private BigDecimal price;

    @ApiModelProperty("Product Quantity")
    private Integer quantity;

    @ApiModelProperty("Product Publish Status: 0 for off-shelve, 1 for shelve")
    private Integer publishStatus;

    @ApiModelProperty("Product Audit Status: 0 not audited, 1 audited")
    private Integer auditStatus;

    @ApiModelProperty("Production Date")
    private Date productionDate;

    @ApiModelProperty("Product Description")
    private String description;

    @ApiModelProperty("Product Entry Time")
    private Date indate;

    @ApiModelProperty("Product Info Last Modified Time")
    private Date modifiedTime;


}
