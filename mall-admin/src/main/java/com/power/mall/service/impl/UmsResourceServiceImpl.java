package com.power.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.power.mall.mapper.UmsResourceCategoryMapper;
import com.power.mall.mapper.UmsResourceMapper;
import com.power.mall.model.UmsResource;
import com.power.mall.model.UmsResourceCategory;
import com.power.mall.model.UmsResourceCategoryExample;
import com.power.mall.model.UmsResourceExample;
import com.power.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/9/11 11:51
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceMapper resourceMapper;

    @Autowired
    private UmsResourceCategoryMapper umsResourceCategoryMapper;



    @Override
    public List<UmsResource> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.createCriteria();
        if (!StrUtil.isEmpty(keyword)){
            criteria.andNameLike("%" + keyword +"%");
        }
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }
}
