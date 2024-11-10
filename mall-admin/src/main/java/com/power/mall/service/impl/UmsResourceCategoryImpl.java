package com.power.mall.service.impl;

import com.power.mall.mapper.UmsResourceCategoryMapper;
import com.power.mall.model.UmsResourceCategory;
import com.power.mall.model.UmsResourceCategoryExample;
import com.power.mall.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsResourceCategoryImpl implements UmsResourceCategoryService {

    @Autowired
    private UmsResourceCategoryMapper umsResourceCategoryMapper;
    @Override
    public List<UmsResourceCategory> listAll() {
        UmsResourceCategoryExample example = new UmsResourceCategoryExample();

        return umsResourceCategoryMapper.selectByExample(example);
    }
}
