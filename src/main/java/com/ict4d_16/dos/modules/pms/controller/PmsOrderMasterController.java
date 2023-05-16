package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.modules.pms.model.PmsOrderMaster;
import com.ict4d_16.dos.modules.pms.service.PmsOrderMasterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Order Info Table
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
@RestController
@RequestMapping("/pms/orderMaster")
public class PmsOrderMasterController {
    @Autowired
    private PmsOrderMasterService pmsOrderMasterService;

    @ApiOperation("Create one order. If created successfully, return order id.")
    @PostMapping("/create")
    public CommonResult<PmsOrderMaster> add(@RequestBody PmsOrderMaster pmsOrderMaster) {
        PmsOrderMaster orderMaster = pmsOrderMasterService.create(pmsOrderMaster);
        if (orderMaster != null) {
            return CommonResult.success(orderMaster, "Create successfully");
        } else {
            return CommonResult.failed("Failed to create");
        }
    }

    @ApiOperation("List all orders.")
    @GetMapping("/list")
    public CommonResult<List<PmsOrderMaster>> list() {
        List<PmsOrderMaster> orderMasterList = pmsOrderMasterService.list();
        if (orderMasterList != null) {
            return CommonResult.success(orderMasterList, "Query successfully");
        } else {
            return CommonResult.failed("Failed to query");
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<PmsOrderMaster> getById(@PathVariable Long id) {
        PmsOrderMaster pmsOrderMaster = pmsOrderMasterService.getById(id);
        if (pmsOrderMasterService != null) {
            return CommonResult.success(pmsOrderMaster, "Query successfully");
        } else {
            return CommonResult.failed("Failed to query");
        }
    }

    @ApiOperation("Delete one order by id.")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteById(@PathVariable Long id) {
        boolean success = pmsOrderMasterService.removeById(id);
        if (success) {
            return CommonResult.success(null, "Delete successfully");
        } else {
            return CommonResult.failed("Failed to delete");
        }
    }
}

