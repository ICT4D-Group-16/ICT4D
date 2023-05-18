package com.ict4d_16.dos.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ict4d_16.dos.modules.pms.model.PmsOrderDetail;
import com.ict4d_16.dos.modules.pms.mapper.PmsOrderDetailMapper;
import com.ict4d_16.dos.modules.pms.model.PmsOrderMaster;
import com.ict4d_16.dos.modules.pms.model.PmsProduct;
import com.ict4d_16.dos.modules.pms.service.PmsOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ict4d_16.dos.modules.pms.service.PmsOrderMasterService;
import com.ict4d_16.dos.modules.pms.service.PmsProductService;
import com.ict4d_16.dos.modules.ums.model.UmsAdmin;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private PmsProductService productService;

    @Override
    @Transactional
    public PmsOrderDetail create(PmsOrderDetail pmsOrderDetail) {
        PmsOrderDetail orderDetail = new PmsOrderDetail();
        BeanUtils.copyProperties(pmsOrderDetail, orderDetail);
        orderDetail.setOrderDetailId(null);
        orderDetail.setModifiedTime(new Date());
        // check product and order valid
        PmsProduct product = productService.getById(orderDetail.getProductId());
        if (product == null) {
            throw new RuntimeException("Product not found. Please check the product id.");
        }
        orderDetail.setProductPrice(product.getPrice());
        PmsOrderMaster order = orderMasterService.getById(orderDetail.getOrderId());
        if (order == null) {
            throw new RuntimeException("Order not found. Please check the order id.");
        }
        // subtract product quantity
        product.setQuantity(product.getQuantity() - orderDetail.getProductQuantity());
        if (product.getQuantity() < 0) {
            throw new RuntimeException("Product quantity is not enough. Order can not be created.");
        } else if (product.getQuantity() == 0) {
            // product is out of stock
            product.setPublishStatus(0);
        }
        LambdaUpdateWrapper<PmsProduct> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(PmsProduct::getProductId, product.getProductId());
        boolean successUpdateProduct = productService.update(product, updateWrapper);
        if (!successUpdateProduct) {
            throw new RuntimeException("Product quantity update failed.");
        }
        // update order money
        orderDetail.setProductTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(orderDetail.getProductQuantity())));
        order.setOrderMoney(order.getOrderMoney().add(orderDetail.getProductTotalPrice()));
        boolean successUpdate = orderMasterService.updateById(order);
        if (!successUpdate) {
            throw new RuntimeException("Order money update failed.");
        }
        // create order detail
        boolean success = save(orderDetail);
        if (!success) {
            throw new RuntimeException("Order detail create failed.");
        }
        return orderDetail;
    }
}
