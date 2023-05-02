package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.modules.pms.model.PmsOrderDetail;
import com.ict4d_16.dos.modules.pms.service.PmsOrderDetailService;
import com.ict4d_16.dos.modules.ums.dto.UmsAdminParam;
import com.ict4d_16.dos.modules.ums.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Order detail table. Each order has N order detail table. 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/pms/pmsOrderDetail")
public class PmsOrderDetailController {
    @Autowired
    private PmsOrderDetailService pmsOrderDetailService;

    @PostMapping("/add")
    public String add(@RequestBody PmsOrderDetail pmsOrderDetail) {
        boolean success = pmsOrderDetailService.save(pmsOrderDetail);
        return success ? "success" : "failure";
    }

    @GetMapping("/list")
    public List<PmsOrderDetail> list() {
        return pmsOrderDetailService.list();
    }

    @GetMapping("/get/{id}")
    public PmsOrderDetail getById(@PathVariable Long id) {
        return pmsOrderDetailService.getById(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody PmsOrderDetail pmsOrderDetail) {
        boolean success = pmsOrderDetailService.updateById(pmsOrderDetail);
        return success ? "success" : "failure";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        boolean success = pmsOrderDetailService.removeById(id);
        return success ? "success" : "failure";
    }
}

