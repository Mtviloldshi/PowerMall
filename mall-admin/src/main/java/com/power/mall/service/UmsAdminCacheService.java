package com.power.mall.service;

import com.power.mall.model.UmsAdmin;
import com.power.mall.model.UmsResource;

import java.util.List;

/**
 * 后台用户缓存操作
 */
public interface UmsAdminCacheService {
    /**
     * 设置缓存信息
     */
    void setResouceList(Long adminId, List<UmsResource> resourceList);
    /**
     * 获取当前缓存用户的信息
     */
    UmsAdmin getAdmin(String username);    /**
     * 设置当前缓存用户的信息
     */
    void setAdmin(UmsAdmin umsAdmin);

    List<UmsResource> getResourceList(Long adminId);
}
