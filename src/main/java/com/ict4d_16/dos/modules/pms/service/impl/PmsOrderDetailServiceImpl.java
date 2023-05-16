package com.ict4d_16.dos.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ict4d_16.dos.modules.pms.model.PmsOrderDetail;
import com.ict4d_16.dos.modules.pms.mapper.PmsOrderDetailMapper;
import com.ict4d_16.dos.modules.pms.model.PmsOrderMaster;
import com.ict4d_16.dos.modules.pms.service.PmsOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ict4d_16.dos.modules.pms.service.PmsOrderMasterService;
import com.ict4d_16.dos.modules.ums.model.UmsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Order detail table. Each order has N order detail table. 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-05-01
 */
@Service
public class PmsOrderDetailServiceImpl extends ServiceImpl<PmsOrderDetailMapper, PmsOrderDetail> implements PmsOrderDetailService {
    @Autowired
    private PmsOrderMasterService orderMasterService;

    @Override
    @Transactional
    public PmsOrderDetail create(PmsOrderDetail pmsOrderDetail) {
        pmsOrderDetail.setModifiedTime(new Date());
        boolean success = save(pmsOrderDetail);
        if (success) {
            PmsOrderMaster order = orderMasterService.getById(pmsOrderDetail.getOrderId());
            if (order != null) {
                order.setOrderMoney(order.getOrderMoney().add(pmsOrderDetail.getProductPrice().multiply(BigDecimal.valueOf(pmsOrderDetail.getProductQuantity()))));
                boolean successUpdate = orderMasterService.updateById(order);
                if (successUpdate) {
                    return pmsOrderDetail;
                }

            }
        }
        return null;
    }
}
