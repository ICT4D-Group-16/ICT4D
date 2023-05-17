package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.modules.pms.model.PmsRecording;
import com.ict4d_16.dos.modules.pms.model.PmsTranslate;
import com.ict4d_16.dos.modules.pms.service.PmsTranslateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  Product Management System. Translation controller
 * </p>
 *
 * @author par
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/pms/translate")
public class PmsTranslateController {
    @Autowired
    private PmsTranslateService pmsTranslateService;

    @ApiOperation("Add one name translate text for a product.")
    @RequestMapping(value = "/createName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PmsTranslate> create(@Validated @RequestBody PmsTranslate pmsTranslate) {
        pmsTranslate.setCategory(0);
        try {
            PmsTranslate translate = pmsTranslateService.create(pmsTranslate);
            if (translate == null) {
                return CommonResult.failed("Failed to create");
            }
            return CommonResult.success(translate, "Create successfully");
        } catch (Exception e) {
            return CommonResult.failed("Failed to create translation text for product name. " + e.getMessage());
        }
    }

    @ApiOperation("Add one description translate text for a product.")
    @RequestMapping(value = "/createDescription", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PmsTranslate> createDescription(@Validated @RequestBody PmsTranslate pmsTranslate) {
        pmsTranslate.setCategory(1);
        try {
            PmsTranslate translate = pmsTranslateService.create(pmsTranslate);
            if (translate == null) {
                return CommonResult.failed("Failed to create");
            }
            return CommonResult.success(translate, "Create successfully");
        } catch (Exception e) {
            return CommonResult.failed("Failed to create translation text for product description. " + e.getMessage());
        }
    }
}

