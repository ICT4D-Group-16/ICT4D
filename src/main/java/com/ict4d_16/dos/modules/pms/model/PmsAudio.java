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
 * @author macro
 * @since 2023-05-15
 */
@Getter
@Setter
@TableName("pms_audio")
@ApiModel(value = "PmsAudio对象", description = "")
public class PmsAudio implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("The id of translated audio")
    @TableId(value = "audio_id", type = IdType.AUTO)
    private Long audioId;

    @NotNull
    @ApiModelProperty(value = "Product ID", required = true)
    private Long productId;

    @ApiModelProperty("URL of audio file")
    private String url;

    @ApiModelProperty("Translated audio created time")
    private Date createTime;

    @NotEmpty
    @ApiModelProperty(value = "Translated audio language", required = true)
    private String language;


}
