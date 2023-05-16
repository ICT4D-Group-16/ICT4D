package com.ict4d_16.dos.modules.pms.service;

import com.ict4d_16.dos.modules.pms.model.PmsOrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Order Info Table 服务类
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
public interface PmsOrderMasterService extends IService<PmsOrderMaster> {
    /**
     * create one order
     */
    PmsOrderMaster create(PmsOrderMaster pmsOrderMaster);

    /**
     * list all orders
     */
    List<PmsOrderMaster> list();
}
