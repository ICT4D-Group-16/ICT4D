package com.ict4d_16.dos.modules.ums.dto;

import com.ict4d_16.dos.modules.ums.model.UmsMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Admin Menu Node Encapsulation
 * Created by macro on 2020/2/4.
 */
@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
    @ApiModelProperty(value = "sub menu")
    private List<UmsMenuNode> children;
}
