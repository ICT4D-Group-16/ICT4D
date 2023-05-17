package com.ict4d_16.dos.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ict4d_16.dos.modules.pms.model.PmsProduct;
import com.ict4d_16.dos.modules.pms.model.PmsTranslate;
import com.ict4d_16.dos.modules.pms.mapper.PmsTranslateMapper;
import com.ict4d_16.dos.modules.pms.service.PmsProductService;
import com.ict4d_16.dos.modules.pms.service.PmsTranslateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-05-15
 */
@Service
public class PmsTranslateServiceImpl extends ServiceImpl<PmsTranslateMapper, PmsTranslate> implements PmsTranslateService {
    @Autowired
    private PmsProductService pmsProductService;

    @Override
    public PmsTranslate create(PmsTranslate pmsTranslate) {
        PmsTranslate translate = new PmsTranslate();
        BeanUtils.copyProperties(pmsTranslate, translate);
        translate.setTranslateId(null);
        PmsProduct product = pmsProductService.getById(pmsTranslate.getProductId());
        if (product == null) {
            throw new RuntimeException("Product does not exist");
        }
        translate.setCreateTime(new Date());
        boolean success = save(translate);
        if (!success) {
            throw new RuntimeException("Failed to create translation text for product");
        }
        return translate;
    }
}
