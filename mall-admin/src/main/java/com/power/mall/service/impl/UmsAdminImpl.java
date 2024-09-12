package com.power.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.power.mall.bo.AdminUserDetails;
import com.power.mall.common.exception.Asserts;
import com.power.mall.dto.UserLoginDTO;
import com.power.mall.dto.UserRegisterDTO;
import com.power.mall.mapper.UserAdminMapper;
import com.power.mall.model.UmsAdmin;
import com.power.mall.model.UmsResource;
import com.power.mall.service.UmsAdminCacheService;
import com.power.mall.service.UmsAdminService;
import com.power.mall.security.util.JwtTokenUtil;
import com.power.mall.model.UmsExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/6/27 12:12
 */
@Service
public class UmsAdminImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminImpl.class);
    @Autowired
    private UserAdminMapper userAdminMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsAdminCacheService umsAdminCacheService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean register(UserRegisterDTO dto) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername(dto.getUsername());
        umsAdmin.setPassword(dto.getPassword());
        int insert = userAdminMapper.insert(umsAdmin);
        System.out.println(
                "用户名 :"+ dto.getUsername()+
                "密码 :"+ dto.getPassword()+
                "注册是否成功 :"+ insert);
        return true;
    }

    @Override
    public String login(UserLoginDTO loginDTO) {
        String token = null;
        try {
            //用户名不能为空吗，且用户名不能重复出现
            UserDetails userDetails = loadUserByUsername(loginDTO.getUsername());
            LOGGER.info(passwordEncoder.encode(loginDTO.getPassword()));
            String password = userDetails.getPassword();
            if (!passwordEncoder.matches(loginDTO.getPassword(),password)) {
                Asserts.fail("密码不正确");
            }
            if (!userDetails.isEnabled()){
                Asserts.fail("账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(userDetails,null,null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
            //将登录人信息缓存到redis中
            getAdminByUsername(loginDTO.getUsername());
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常：{}",e.getMessage());
        }
        return token;
    }

    @Override
    public List<UmsAdmin> list() {
        List<UmsAdmin> list = userAdminMapper.list();
        return list;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null){
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    private List<UmsResource> getResourceList(Long id) {
        //先从缓存中获取数据
        List<UmsResource> resourceList =umsAdminCacheService.getResourceList(id);
        if (CollUtil.isNotEmpty(resourceList)){
            return resourceList;
        }
        //缓存中没有从数据库获取

        return null;
    }
    //准确来说是从缓存中获取用户信息，并且同时有存储的逻辑
    public UmsAdmin getAdminByUsername(String username) {
        //在存储前，先从redis中查找是否已经存在
        UmsAdmin admin = umsAdminCacheService.getAdmin(username);
        if (admin != null){
            return admin;
        }
        //从数据库中查找，并存入或更新redis
        UmsExample umsExample = new UmsExample();
        umsExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = userAdminMapper.selectByExample(umsExample);
        if (adminList != null && adminList.size() > 0){
            admin = adminList.get(0);
            umsAdminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

//
//    @Override
//    public UmsAdminCacheService getCacheService() {
//        return SpringUtil.getBean(UmsAdminCacheService.class);
//    }

}
