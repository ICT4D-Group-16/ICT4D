package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.modules.pms.model.PmsOrderDetail;
import com.ict4d_16.dos.modules.pms.service.PmsOrderDetailService;
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

