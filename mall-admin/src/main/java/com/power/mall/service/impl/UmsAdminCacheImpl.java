package com.power.mall.service.impl;

import com.power.mall.common.service.RedisService;
import com.power.mall.model.UmsAdmin;
import com.power.mall.model.UmsResource;
import com.power.mall.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Name 用户缓存服务
 * @Author SCH
 * @Date 2024/9/11 2:26
 */
@Service
public class UmsAdminCacheImpl implements UmsAdminCacheService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;


    @Override
    public void setResouceList(Long adminId, List<UmsResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key,resourceList,REDIS_EXPIRE);
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }
    @Override
    public void setAdmin(UmsAdmin umsAdmin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + umsAdmin.getUsername();
        redisService.set(key,umsAdmin,REDIS_EXPIRE);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + adminId;
        return (List<UmsResource>) redisService.get(key);
    }


}
