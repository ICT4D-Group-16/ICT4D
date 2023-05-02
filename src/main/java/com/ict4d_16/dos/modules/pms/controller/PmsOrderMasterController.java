package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.modules.pms.model.PmsOrderMaster;
import com.ict4d_16.dos.modules.pms.service.PmsOrderMasterService;
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
@RequestMapping("/pms/pmsOrderMaster")
public class PmsOrderMasterController {
    @Autowired
    private PmsOrderMasterService pmsOrderMasterService;

    @PostMapping("/add")
    public String add(@RequestBody PmsOrderMaster pmsOrderMaster) {
        boolean success = pmsOrderMasterService.save(pmsOrderMaster);
        return success ? "success" : "failure";
    }

    @GetMapping("/list")
    public List<PmsOrderMaster> list() {
        return pmsOrderMasterService.list();
    }

    @GetMapping("/get/{id}")
    public PmsOrderMaster getById(@PathVariable Long id) {
        return pmsOrderMasterService.getById(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody PmsOrderMaster pmsOrderMaster) {
        boolean success = pmsOrderMasterService.updateById(pmsOrderMaster);
        return success ? "success" : "failure";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        boolean success = pmsOrderMasterService.removeById(id);
        return success ? "success" : "failure";
    }
}

