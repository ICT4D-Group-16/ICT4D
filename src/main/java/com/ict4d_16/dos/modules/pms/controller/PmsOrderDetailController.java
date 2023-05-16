package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.modules.pms.model.PmsOrderDetail;
import com.ict4d_16.dos.modules.pms.service.PmsOrderDetailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Order detail table. Each order has N order detail table.
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
@RestController
@RequestMapping("/pms/orderDetail")
public class PmsOrderDetailController {
    @Autowired
    private PmsOrderDetailService pmsOrderDetailService;

    @ApiOperation(value = "add a new order detail for a specific order.")
    @PostMapping("/create")
    public CommonResult<PmsOrderDetail> add(@RequestBody PmsOrderDetail pmsOrderDetail) {
        PmsOrderDetail orderDetail = pmsOrderDetailService.create(pmsOrderDetail);
        if (orderDetail != null) {
            return CommonResult.success(orderDetail, "Create successfully.");
        } else {
            return CommonResult.failed("Create failed.");
        }
    }
}

