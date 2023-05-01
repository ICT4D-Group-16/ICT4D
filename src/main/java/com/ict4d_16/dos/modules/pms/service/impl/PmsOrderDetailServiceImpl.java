package com.ict4d_16.dos.modules.pms.service.impl;

import com.ict4d_16.dos.modules.pms.model.PmsOrderDetail;
import com.ict4d_16.dos.modules.pms.mapper.PmsOrderDetailMapper;
import com.ict4d_16.dos.modules.pms.service.PmsOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
