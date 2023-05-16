package com.ict4d_16.dos.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ict4d_16.dos.modules.pms.model.PmsTranslate;
import com.ict4d_16.dos.modules.pms.mapper.PmsTranslateMapper;
import com.ict4d_16.dos.modules.pms.service.PmsTranslateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    @Override
    public PmsTranslate create(Long productId, String language, String text) {
        if (productId != null
                && StrUtil.isNotEmpty(language)
                && StrUtil.isNotEmpty(text)) {
            PmsTranslate pmsTranslate = new PmsTranslate();
            pmsTranslate.setProductId(productId);
            pmsTranslate.setLanguage(language);
            pmsTranslate.setText(text);
            pmsTranslate.setCreateTime(new Date());
            boolean success = save(pmsTranslate);
            if (success) {
                return pmsTranslate;
            } else {
                return null;
            }
        }
        return null;
    }
}
