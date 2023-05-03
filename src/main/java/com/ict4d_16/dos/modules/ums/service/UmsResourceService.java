package com.ict4d_16.dos.modules.ums.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ict4d_16.dos.modules.ums.model.UmsResource;

import java.util.List;

/**
 * Resource Management Service
 * Created by macro on 2020/2/2.
 */
public interface UmsResourceService extends IService<UmsResource> {
    /**
     * add resource
     */
    boolean create(UmsResource umsResource);

    /**
     * update resource
     */
    boolean update(Long id, UmsResource umsResource);

    /**
     * delete resource
     */
    boolean delete(Long id);

    /**
     * Paging Search Resources
     */
    Page<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);
}
