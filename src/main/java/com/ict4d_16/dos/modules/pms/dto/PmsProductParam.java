package com.ict4d_16.dos.modules.pms.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
