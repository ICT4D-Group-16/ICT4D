package com.ict4d_16.dos.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ict4d_16.dos.modules.pms.model.PmsAudio;
import com.ict4d_16.dos.modules.pms.mapper.PmsAudioMapper;
import com.ict4d_16.dos.modules.pms.model.PmsProduct;
import com.ict4d_16.dos.modules.pms.service.PmsAudioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ict4d_16.dos.modules.pms.service.PmsProductService;
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
public class PmsAudioServiceImpl extends ServiceImpl<PmsAudioMapper, PmsAudio> implements PmsAudioService {
    @Autowired
    private PmsProductService pmsProductService;

    @Override
    public PmsAudio create(PmsAudio pmsAudio) {
        PmsAudio audio = new PmsAudio();
        BeanUtils.copyProperties(pmsAudio, audio);
        audio.setCreateTime(new Date());
        PmsProduct product = pmsProductService.getById(pmsAudio.getProductId());
        if (product == null) {
            throw new RuntimeException("Product does not exist");
        }
        boolean success = save(audio);
        if (!success) {
            throw new RuntimeException("Failed to create translation audio for product");
        }
        return audio;
    }
}
