package com.ict4d_16.dos.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.modules.pms.dto.PmsProductParam;
import com.ict4d_16.dos.modules.pms.model.PmsAudio;
import com.ict4d_16.dos.modules.pms.model.PmsProduct;
import com.ict4d_16.dos.modules.pms.mapper.PmsProductMapper;
import com.ict4d_16.dos.modules.pms.model.PmsRecording;
import com.ict4d_16.dos.modules.pms.model.PmsTranslate;
import com.ict4d_16.dos.modules.pms.service.PmsAudioService;
import com.ict4d_16.dos.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ict4d_16.dos.modules.pms.service.PmsRecordingService;
import com.ict4d_16.dos.modules.pms.service.PmsTranslateService;
import com.ict4d_16.dos.modules.ums.model.UmsAdmin;
import com.ict4d_16.dos.modules.ums.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Product Info Table 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-05-01
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private PmsTranslateService pmsTranslateService;
    @Autowired
    private PmsAudioService pmsAudioService;
    @Autowired
    private PmsRecordingService pmsRecordingService;

    @Override
    public PmsProduct create(PmsProductParam pmsProductParam) {
        PmsProduct pmsProduct = new PmsProduct();
        BeanUtils.copyProperties(pmsProductParam, pmsProduct);
        pmsProduct.setAuditStatus(0);
        pmsProduct.setIndate(new Date());
        pmsProduct.setModifiedTime(new Date());
        pmsProduct.setPublishStatus(0);
        UmsAdmin seller = adminService.getAdminByPhone(pmsProductParam.getPhone());
        if (seller == null) {
            throw new IllegalArgumentException("Seller does not exist");
        }
        pmsProduct.setSupplierUserId(seller.getId());
        boolean success = save(pmsProduct);
        if (!success) {
            throw new RuntimeException("Failed to create product");
        }
        return pmsProduct;
    }

    @Override
    public List<PmsProduct> list() {
        List<PmsProduct> productList = baseMapper.selectList(null);
        if (productList == null || productList.size() == 0) {
            throw new RuntimeException("No product found");
        }
        return getTranslationAndAudio(productList);
    }

    @Override
    public List<PmsProduct> getByRecordingId(Long recordingId) {
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("recording_id", recordingId);
        List<PmsProduct> productList = baseMapper.selectList(queryWrapper);
        if (productList == null || productList.size() == 0) {
            throw new RuntimeException("No product found");
        }
        return getTranslationAndAudio(productList);
    }

    @Override
    public List<PmsProduct> getBySupplierUserId(Long supplierUserId) {
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplier_user_id", supplierUserId);
        List<PmsProduct> productList = baseMapper.selectList(queryWrapper);
        if (productList == null || productList.size() == 0) {
            throw new RuntimeException("No product found");
        }
        return getTranslationAndAudio(productList); }

    @Override
    public PmsProduct getById(Long id) {
        PmsProduct product = baseMapper.selectById(id);
        if (product == null) {
            throw new RuntimeException("No product found");
        }
        return getTranslationAndAudio(Collections.singletonList(product)).get(0);
    }

    private List<PmsProduct> getTranslationAndAudio(List<PmsProduct> productList) {
        if (productList == null || productList.size() == 0) {
            return null;
        }
        for (PmsProduct product : productList) {
            QueryWrapper<PmsTranslate> nameTranslateQueryWrapper = new QueryWrapper<>();
            nameTranslateQueryWrapper.eq("product_id", product.getProductId())
                    .eq("category", 0);
            List<PmsTranslate> nameTranslateList = pmsTranslateService.list(nameTranslateQueryWrapper);
            product.setNameTranslates(nameTranslateList);
            QueryWrapper<PmsTranslate> descriptionTranslateQueryWrapper = new QueryWrapper<>();
            descriptionTranslateQueryWrapper.eq("product_id", product.getProductId())
                    .eq("category", 1);
            List<PmsTranslate> descriptionTranslateList = pmsTranslateService.list(descriptionTranslateQueryWrapper);
            product.setDescriptionTranslates(descriptionTranslateList);

            QueryWrapper<PmsAudio> nameAudioQueryWrapper = new QueryWrapper<>();
            nameAudioQueryWrapper.eq("product_id", product.getProductId())
                    .eq("category", 0);
            List<PmsAudio> nameAudioList = pmsAudioService.list(nameAudioQueryWrapper);
            product.setNameAudios(nameAudioList);
            QueryWrapper<PmsAudio> descriptionAudioQueryWrapper = new QueryWrapper<>();
            descriptionAudioQueryWrapper.eq("product_id", product.getProductId())
                    .eq("category", 1);
            List<PmsAudio> descriptionAudioList = pmsAudioService.list(descriptionAudioQueryWrapper);
            product.setDescriptionAudios(descriptionAudioList);
        }
        return productList;
    }

    @Override
    public PmsProduct updateByProductId(PmsProduct pmsProduct) {
        PmsProduct product = new PmsProduct();
        BeanUtils.copyProperties(pmsProduct, product);
        product.setModifiedTime(new Date());
        // Check if the seller exists
        UmsAdmin seller = adminService.getById(pmsProduct.getSupplierUserId());
        if (seller == null) {
            throw new IllegalArgumentException("Seller does not exist");
        }
        // Check if the recording exists
        PmsRecording recording = pmsRecordingService.getById(pmsProduct.getRecordingId());
        if (recording == null) {
            throw new IllegalArgumentException("Recording does not exist");
        }
        boolean success = updateById(product);
        if (!success) {
            throw new RuntimeException("Failed to update product");
        }
        return getTranslationAndAudio(Collections.singletonList(product)).get(0);
    }
}
