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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author par
 * @since 2023-05-15
 */
@Getter
@Setter
@TableName("pms_translate")
@ApiModel(value = "PmsTranslate object", description = "")
public class PmsTranslate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Translation Text Id")
    @TableId(value = "translate_id", type = IdType.AUTO)
    private Long translateId;

    @NotNull
    @ApiModelProperty(value = "Product Id", required = true)
    private Long productId;

    @NotEmpty
    @ApiModelProperty(value = "Translated text", required = true)
    private String text;

    @ApiModelProperty("Translated text created time")
    private Date createTime;

    @NotEmpty
    @ApiModelProperty(value = "Translated text language", required = true)
    private String language;


}
