package com.ict4d_16.dos.modules.ums.service.impl;

import com.ict4d_16.dos.modules.ums.model.UmsAddress;
import com.ict4d_16.dos.modules.ums.mapper.UmsAddressMapper;
import com.ict4d_16.dos.modules.ums.service.UmsAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User address table. One user can has multiple addresses. 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-05-01
 */
@Service
public class UmsAddressServiceImpl extends ServiceImpl<UmsAddressMapper, UmsAddress> implements UmsAddressService {

}
