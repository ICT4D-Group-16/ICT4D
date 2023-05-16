package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.modules.pms.dto.PmsProductParam;
import com.ict4d_16.dos.modules.pms.model.PmsAudio;
import com.ict4d_16.dos.modules.pms.model.PmsProduct;
import com.ict4d_16.dos.modules.pms.model.PmsRecording;
import com.ict4d_16.dos.modules.pms.model.PmsTranslate;
import com.ict4d_16.dos.modules.pms.service.PmsAudioService;
import com.ict4d_16.dos.modules.pms.service.PmsProductService;
import com.ict4d_16.dos.modules.pms.service.PmsTranslateService;
import com.ict4d_16.dos.modules.ums.dto.UmsAdminParam;
//import com.sun.org.apache.xpath.internal.objects.XNull;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Product Info Table
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
@RestController
@RequestMapping("/pms/product")
public class PmsProductController {
    @Autowired
    private PmsProductService pmsProductService;

    @ApiOperation("Create a new product")
    @PostMapping("/create")
    public CommonResult<PmsProduct> create(@Validated @RequestBody PmsProductParam pmsProductParam) {
        PmsProduct product = pmsProductService.create(pmsProductParam);
        if (product != null) {
            return CommonResult.success(product, "Create successfully");
        } else {
            return CommonResult.failed("Failed to create");
        }
    }

    @ApiOperation("List all products")
    @GetMapping("/list")
    public CommonResult<List<PmsProduct>> list() {
        List<PmsProduct> productList = pmsProductService.list();
        if (productList == null || productList.size() == 0) {
            return CommonResult.failed("No product found");
        }
        return CommonResult.success(productList, "Get product list successfully");
    }

    @ApiOperation("Get a product by id")
    @GetMapping("/get/{id}")
    public CommonResult<PmsProduct> getById(@PathVariable Long id) {
        PmsProduct product = pmsProductService.getById(id);
        if (product == null) {
            return CommonResult.failed("No product found");
        } else {
            return CommonResult.success(product, "Get product successfully");
        }
    }

    @PutMapping("/update")
    public String update(@RequestBody PmsProduct pmsProduct) {
        boolean success = pmsProductService.updateById(pmsProduct);
        return success ? "success" : "failure";
    }

    @ApiOperation("Delete a product by id")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteById(@PathVariable Long id) {
        boolean success = pmsProductService.removeById(id);
        if (success) {
            return CommonResult.success("Delete successfully");
        } else {
            return CommonResult.failed("Delete failed");
        }
    }
}

