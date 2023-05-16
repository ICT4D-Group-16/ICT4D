package com.ict4d_16.dos.modules.pms.service;

import com.ict4d_16.dos.modules.pms.model.PmsOrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * Order detail table. Each order has N order detail table. 服务类
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
public interface PmsOrderDetailService extends IService<PmsOrderDetail> {
    /**
     * create one order detail
     */
    PmsOrderDetail create(PmsOrderDetail pmsOrderDetail);
}
