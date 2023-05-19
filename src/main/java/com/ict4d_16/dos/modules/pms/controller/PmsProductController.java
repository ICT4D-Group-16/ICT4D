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

import java.util.*;

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
        try {
            PmsProduct product = pmsProductService.create(pmsProductParam);
            if (product == null) {
                return CommonResult.failed("Failed to create");
            }
            return CommonResult.success(product, "Create successfully");
        } catch (Exception e) {
            return CommonResult.failed("Failed to create product. " + e.getMessage());
        }
    }

    @ApiOperation("List all products")
    @GetMapping("/list")
    public CommonResult<List<PmsProduct>> list() {
        try {
            List<PmsProduct> productList = pmsProductService.list();
            if (productList == null || productList.size() == 0) {
                return CommonResult.failed("No product found");
            }
            return CommonResult.success(productList, "Get product list successfully");
        } catch (Exception e) {
            return CommonResult.failed("Failed to get product list. " + e.getMessage());
        }
    }

    @ApiOperation("Get a product. Support query by product id or recording id")
    @PostMapping("/search")
    public CommonResult<List<PmsProduct>> search(@RequestBody Map<String, String> requestMap) {
        try {
            if (requestMap.containsKey("productId")) {
                PmsProduct product = pmsProductService.getById(Long.parseLong(requestMap.get("productId")));
                if (product == null) {
                    return CommonResult.failed("No product found");
                }
                List<PmsProduct> pmsProducts = new ArrayList<>();
                pmsProducts.add(product);
                return CommonResult.success(pmsProducts, "Get product successfully");
            }
            if (requestMap.containsKey("recordingId")) {
                List<PmsProduct> products = pmsProductService.getByRecordingId(Long.parseLong(requestMap.get("recordingId")));
                if (products == null) {
                    return CommonResult.failed("No product found");
                }
                return CommonResult.success(products, "Get product successfully");
            }
            if (requestMap.containsKey("supplierUserId")) {
                List<PmsProduct> products = pmsProductService.getBySupplierUserId(Long.parseLong(requestMap.get("supplierUserId")));
                if (products == null) {
                    return CommonResult.failed("No product found");
                }
                return CommonResult.success(products, "Get product successfully");
            }
        } catch (Exception e) {
            return CommonResult.failed("Failed to get product. " + e.getMessage());
        }
        return CommonResult.failed("No product found");
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

    @ApiOperation("Get a product by record id")
    @GetMapping("/getByRecord/{id}")
    public CommonResult<PmsProduct> getByRecordId(@PathVariable Long id) {

        List<PmsProduct> products = pmsProductService.getByRecordingId(id);

        if (products != null) {
            return CommonResult.success(products.get(0), "Get product successfully");
        } else {
            return CommonResult.failed("No product found");
        }
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

