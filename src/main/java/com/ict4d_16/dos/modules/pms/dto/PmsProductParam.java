package com.ict4d_16.dos.modules.pms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Product create Parameters
 * Created by par.
 */
@Getter
@Setter
public class PmsProductParam {

    @NotEmpty
    @ApiModelProperty(value = "Product Name", required = true)
    private String productName;

    @NotEmpty
    @ApiModelProperty(value = "Seller phone number", required = true)
    private String phone;

    @NotNull
    @ApiModelProperty(value = "Product Price, US Dollar", required = true)
    private BigDecimal price;

    @NotNull
    @ApiModelProperty(value = "Product Quantity", required = true)
    private Integer quantity;

    @ApiModelProperty("Product Description")
    private String description;

    @ApiModelProperty("Recording ID")
    private Long recordingId;
}
