package com.power.mall.service;

import com.power.mall.dto.UserLoginDTO;
import com.power.mall.dto.UserRegisterDTO;
import com.power.mall.model.UmsAdmin;
import com.power.mall.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/6/27 12:10
 */

public interface UmsAdminService {
    public Boolean register(UserRegisterDTO userRegisterDTO);

    String login(UserLoginDTO loginDTO);

    List<UmsAdmin> list(String keyword,Integer pageSize,Integer pageNum);

    UserDetails loadUserByUsername(String username);

    UmsAdmin getAdminByUsername(String username);

    List<UmsRole> getRoleList(Long adminId);
    /**
     * 获取缓存服务
     */
//    UmsAdminCacheService getCacheService();
}
