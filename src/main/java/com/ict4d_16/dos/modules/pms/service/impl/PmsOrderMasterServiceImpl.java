package com.ict4d_16.dos.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ict4d_16.dos.modules.pms.model.*;
import com.ict4d_16.dos.modules.pms.mapper.PmsOrderMasterMapper;
import com.ict4d_16.dos.modules.pms.service.PmsOrderDetailService;
import com.ict4d_16.dos.modules.pms.service.PmsOrderMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Order Info Table 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-05-01
 */
@Service
public class PmsOrderMasterServiceImpl extends ServiceImpl<PmsOrderMasterMapper, PmsOrderMaster> implements PmsOrderMasterService {
    @Autowired
    private PmsOrderDetailService pmsOrderDetailService;

    @Override
    public PmsOrderMaster create(PmsOrderMaster pmsOrderMaster) {
        pmsOrderMaster.setCreateTime(new Date());
        pmsOrderMaster.setModifiedTime(new Date());
        pmsOrderMaster.setOrderStatus(0);
        boolean success = save(pmsOrderMaster);
        if (success) {
            return pmsOrderMaster;
        }
        else {
            return null;
        }
    }

    @Override
    public List<PmsOrderMaster> list() {
        List<PmsOrderMaster> orderMasters = baseMapper.selectList(null);
        if (orderMasters == null || orderMasters.size() == 0) {
            return null;
        }
        for (PmsOrderMaster orderMaster : orderMasters) {
            QueryWrapper<PmsOrderDetail> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_id", orderMaster.getOrderId());
            List<PmsOrderDetail> orderDetails = pmsOrderDetailService.list(queryWrapper);
            orderMaster.setOrderDetails(orderDetails);
        }
        return orderMasters;
    }

    @Override
    public PmsOrderMaster getByOrderId(Long orderId) {
        PmsOrderMaster orderMaster = baseMapper.selectById(orderId);
        if (orderMaster == null) {
            throw new RuntimeException("Order not found");
        }
        QueryWrapper<PmsOrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        List<PmsOrderDetail> orderDetails = pmsOrderDetailService.list(queryWrapper);
        orderMaster.setOrderDetails(orderDetails);
        return orderMaster;
    }
}
