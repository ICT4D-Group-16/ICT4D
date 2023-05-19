package com.ict4d_16.dos.modules.pms.service;

import com.ict4d_16.dos.modules.pms.dto.PmsProductParam;
import com.ict4d_16.dos.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Product Info Table Service
 * </p>
 *
 * @since 2023-05-01
 * @version 1.0
 */
public interface PmsProductService extends IService<PmsProduct> {
    /**
     * add product
     */
    PmsProduct create(PmsProductParam pmsProductParam);

    /**
     * list all products
     */
    List<PmsProduct> list();

    /**
     * get product by recording id
     */
    List<PmsProduct> getByRecordingId(Long recordingId);

    /**
     * get product by supplier user id
     */
    List<PmsProduct> getBySupplierUserId(Long supplierUserId);

    /**
     * get product by id
     */
    PmsProduct getById(Long id);

    /**
     * update product by id
     */
    PmsProduct updateByProductId(PmsProduct pmsProduct);
}
