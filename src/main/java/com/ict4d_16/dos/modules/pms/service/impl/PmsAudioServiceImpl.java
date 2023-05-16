package com.ict4d_16.dos.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ict4d_16.dos.modules.pms.model.PmsAudio;
import com.ict4d_16.dos.modules.pms.mapper.PmsAudioMapper;
import com.ict4d_16.dos.modules.pms.service.PmsAudioService;
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
public class PmsAudioServiceImpl extends ServiceImpl<PmsAudioMapper, PmsAudio> implements PmsAudioService {
    @Override
    public PmsAudio create(Long productId, String language, String url) {
        PmsAudio pmsAudio = new PmsAudio();
        pmsAudio.setProductId(productId);
        pmsAudio.setLanguage(language);
        pmsAudio.setUrl(url);
        pmsAudio.setCreateTime(new Date());
        boolean success = save(pmsAudio);
        if (success) {
            return pmsAudio;
        } else {
            return null;
        }
    }
}
