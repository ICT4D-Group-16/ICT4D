package com.ict4d_16.dos.modules.pms.service;

import com.ict4d_16.dos.modules.pms.model.PmsAudio;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ict4d_16.dos.modules.pms.model.PmsTranslate;

/**
 * <p>
 *  Product Management System. Audio service
 * </p>
 *
 * @author macro
 * @since 2023-05-15
 */
public interface PmsAudioService extends IService<PmsAudio> {
    /**
     * add Translation audio
     */
    PmsAudio create(PmsAudio pmsAudio);
}
