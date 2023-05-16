package com.ict4d_16.dos.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.modules.pms.dto.PmsProductParam;
import com.ict4d_16.dos.modules.pms.model.PmsAudio;
import com.ict4d_16.dos.modules.pms.model.PmsProduct;
import com.ict4d_16.dos.modules.pms.mapper.PmsProductMapper;
import com.ict4d_16.dos.modules.pms.model.PmsTranslate;
import com.ict4d_16.dos.modules.pms.service.PmsAudioService;
import com.ict4d_16.dos.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ict4d_16.dos.modules.pms.service.PmsTranslateService;
import com.ict4d_16.dos.modules.ums.model.UmsAdmin;
import com.ict4d_16.dos.modules.ums.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public PmsProduct create(PmsProductParam pmsProductParam) {
        PmsProduct pmsProduct = new PmsProduct();
        BeanUtils.copyProperties(pmsProductParam, pmsProduct);
        pmsProduct.setAuditStatus(0);
        pmsProduct.setIndate(new Date());
        pmsProduct.setModifiedTime(new Date());
        pmsProduct.setPublishStatus(0);
        UmsAdmin seller = adminService.getAdminByPhone(pmsProductParam.getPhone());
        pmsProduct.setSupplierUserId(seller.getId());
        boolean success = save(pmsProduct);
        if (success) {
            return pmsProduct;
        } else {
            return null;
        }
    }

    @Override
    public List<PmsProduct> list() {
        List<PmsProduct> productList = baseMapper.selectList(null);
        if (productList == null || productList.size() == 0) {
            return null;
        }
        for (PmsProduct product : productList) {
            QueryWrapper<PmsTranslate> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("product_id", product.getProductId());
            List<PmsTranslate> translateList = pmsTranslateService.list(queryWrapper);
            product.setTranslates(translateList);
            QueryWrapper<PmsAudio> audioQueryWrapper = new QueryWrapper<>();
            audioQueryWrapper.eq("product_id", product.getProductId());
            List<PmsAudio> audioList = pmsAudioService.list(audioQueryWrapper);
            product.setAudios(audioList);
        }
        return productList;
    }
}
