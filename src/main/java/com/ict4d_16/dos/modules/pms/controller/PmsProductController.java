package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.modules.pms.model.PmsProduct;
import com.ict4d_16.dos.modules.pms.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Product Info Table 前端控制器
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
@RestController
@RequestMapping("/pms/pmsProduct")
public class PmsProductController {
    @Autowired
    private PmsProductService pmsProductService;

    @PostMapping("/add")
    public String add(@RequestBody PmsProduct pmsProduct) {
        boolean success = pmsProductService.save(pmsProduct);
        return success ? "success" : "failure";
    }

    @GetMapping("/list")
    public List<PmsProduct> list() {
        return pmsProductService.list();
    }

    @GetMapping("/get/{id}")
    public PmsProduct getById(@PathVariable Long id) {
        return pmsProductService.getById(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody PmsProduct pmsProduct) {
        boolean success = pmsProductService.updateById(pmsProduct);
        return success ? "success" : "failure";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        boolean success = pmsProductService.removeById(id);
        return success ? "success" : "failure";
    }
}

