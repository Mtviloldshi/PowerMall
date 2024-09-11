package com.power.mall.service.impl;

import com.power.mall.model.UmsResource;
import com.power.mall.service.UmsResourceService;
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

//    @Autowired
//    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResource> listAll() {
        return new ArrayList<>();
    }
}
