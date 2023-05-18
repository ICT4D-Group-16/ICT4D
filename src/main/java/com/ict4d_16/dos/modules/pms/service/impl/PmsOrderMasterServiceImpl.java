package com.ict4d_16.dos.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ict4d_16.dos.modules.pms.model.*;
import com.ict4d_16.dos.modules.pms.mapper.PmsOrderMasterMapper;
import com.ict4d_16.dos.modules.pms.service.PmsOrderDetailService;
import com.ict4d_16.dos.modules.pms.service.PmsOrderMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ict4d_16.dos.modules.pms.service.PmsProductService;
import com.ict4d_16.dos.modules.ums.model.UmsAdmin;
import com.ict4d_16.dos.modules.ums.service.UmsAdminService;
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
    @Autowired
    private PmsProductService pmsProductService;
    @Autowired
    private UmsAdminService umsAdminService;

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
            getOrderDetails(orderMaster);
        }
        return orderMasters;
    }

    @Override
    public PmsOrderMaster getByOrderId(Long orderId) {
        PmsOrderMaster orderMaster = baseMapper.selectById(orderId);
        if (orderMaster == null) {
            throw new RuntimeException("Order not found");
        }
        getOrderDetails(orderMaster);
        return orderMaster;
    }

    @Override
    public List<PmsOrderMaster> getByUserId(Long userId) {
        QueryWrapper<PmsOrderMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<PmsOrderMaster> orderMasters = baseMapper.selectList(queryWrapper);
        if (orderMasters == null || orderMasters.size() == 0) {
            throw new RuntimeException("Order not found");
        }
        for (PmsOrderMaster orderMaster : orderMasters) {
            getOrderDetails(orderMaster);
        }
        return orderMasters;
    }

    private void getOrderDetails(PmsOrderMaster pmsOrderMaster) {
        QueryWrapper<PmsOrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", pmsOrderMaster.getOrderId());
        List<PmsOrderDetail> orderDetails = pmsOrderDetailService.list(queryWrapper);
        for (PmsOrderDetail orderDetail : orderDetails) {
            QueryWrapper<PmsProduct> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("product_id", orderDetail.getProductId());
            PmsProduct product = pmsProductService.getOne(queryWrapper1);
            if (product != null) {
                QueryWrapper<UmsAdmin> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("id", product.getSupplierUserId());
                UmsAdmin supplier = umsAdminService.getOne(queryWrapper2);
                if (supplier != null) {
                    orderDetail.setSellerNickname(supplier.getNickName());
                    orderDetail.setSellerPhone(supplier.getPhone());
                }
            }
        }
        pmsOrderMaster.setOrderDetails(orderDetails);
    }
}
