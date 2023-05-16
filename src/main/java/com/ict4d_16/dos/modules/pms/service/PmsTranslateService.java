package com.ict4d_16.dos.modules.pms.service;

import com.ict4d_16.dos.modules.pms.dto.PmsProductParam;
import com.ict4d_16.dos.modules.pms.model.PmsTranslate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  Product Management System. Translation service
 * </p>
 *
 * @author macro
 * @since 2023-05-15
 */
public interface PmsTranslateService extends IService<PmsTranslate> {
    /**
     * add Translation
     */
    PmsTranslate create(Long productId, String language, String text);
}
