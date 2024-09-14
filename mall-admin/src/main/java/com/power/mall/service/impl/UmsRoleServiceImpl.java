package com.power.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.power.mall.dao.UmsRoleDao;
import com.power.mall.mapper.UmsAdminRoleRelationMapper;
import com.power.mall.mapper.UmsMenuMapper;
import com.power.mall.mapper.UmsRoleMapper;
import com.power.mall.mapper.UmsRoleMenuRelationMapper;
import com.power.mall.model.*;
import com.power.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/9/13 7:04
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsMenuMapper menuMapper;
    @Autowired
    private UmsAdminRoleRelationMapper roleRelationMapper;
    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private UmsRoleDao roleDao;
    @Autowired
    private UmsRoleMapper roleMapper;
    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        /**
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        List<UmsAdminRoleRelation> umsAdminRoleRelations = roleRelationMapper.selectByExample(example);
        //查询出一个用户对应的多个角色关联关系
        List<Long> roleList = umsAdminRoleRelations.stream().map(a -> {
            return a.getRoleId();
        }).collect(Collectors.toList());
        UmsRoleMenuRelationExample menuRelationExample = new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdIn(roleList);
        List<UmsRoleMenuRelation> umsRoleMenuRelations = roleMenuRelationMapper.selectByExample(menuRelationExample);
        List<Long> menuIdList = umsRoleMenuRelations.stream().map(a -> {
            return a.getMenuId();
        }).collect(Collectors.toList());
        UmsMenuExample umsMenuExample = new UmsMenuExample();
        umsMenuExample.createCriteria().andIdIn(menuIdList);
        List<UmsMenu> umsMenus = menuMapper.selectByExample(umsMenuExample);
        */
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return roleDao.getRoleList(adminId);
    }

    @Override
    public List<UmsRole> listAll() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UmsRoleExample example = new UmsRoleExample();
        UmsRoleExample.Criteria criteria = example.createCriteria();
        if (!StrUtil.isEmpty(keyword)){
            criteria.andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }
}
