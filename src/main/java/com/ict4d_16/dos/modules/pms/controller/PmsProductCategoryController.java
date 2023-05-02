package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.modules.pms.model.PmsProductCategory;
import com.ict4d_16.dos.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Product Category Table
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
@RestController
@RequestMapping("/pms/pmsProductCategory")
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryService pmsProductCategoryService;

    @PostMapping("/add")
    public String add(@RequestBody PmsProductCategory pmsProductCategory) {
        boolean success = pmsProductCategoryService.save(pmsProductCategory);
        return success ? "success" : "failure";
    }

    @GetMapping("/list")
    public List<PmsProductCategory> list() {
        return pmsProductCategoryService.list();
    }

    @GetMapping("/get/{id}")
    public PmsProductCategory getById(@PathVariable Long id) {
        return pmsProductCategoryService.getById(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody PmsProductCategory pmsProductCategory) {
        boolean success = pmsProductCategoryService.updateById(pmsProductCategory);
        return success ? "success" : "failure";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        boolean success = pmsProductCategoryService.removeById(id);
        return success ? "success" : "failure";
    }
}

