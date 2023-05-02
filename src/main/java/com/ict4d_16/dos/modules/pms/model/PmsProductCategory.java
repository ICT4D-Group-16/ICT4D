package com.ict4d_16.dos.modules.pms.model;

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
 * Product Catagory Table
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
@Getter
@Setter
@TableName("pms_product_category")
@ApiModel(value = "PmsProductCategory Object", description = "Product Catagory Table")
public class PmsProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Category ID")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty("Category Name")
    private String categoryName;

    @ApiModelProperty("Category Last Modified Time")
    private Date modifiedTime;


}
