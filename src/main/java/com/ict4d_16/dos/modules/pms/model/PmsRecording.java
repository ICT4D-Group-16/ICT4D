package com.ict4d_16.dos.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Product Recording Table
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
@Data
@TableName("pms_recording")
@ApiModel(value = "PmsProductRecording Object", description = "Product Recording Table")
public class PmsRecording implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Recording ID")
    @TableId(value = "recording_id", type = IdType.AUTO)
    private Long recordingId;

    @ApiModelProperty("Phone")
    private String phone;

    @ApiModelProperty("url")
    private String url;

    @ApiModelProperty("Recording created time")
    private Date createTime;

    @ApiModelProperty("status")
    private Integer status;

}
