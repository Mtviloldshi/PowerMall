package com.power.mall.service.impl;

import com.power.mall.common.api.CommonResult;
import com.power.mall.dto.UmsMenuTreeListDTO;
import com.power.mall.mapper.UmsMenuMapper;
import com.power.mall.model.UmsMenu;
import com.power.mall.model.UmsMenuExample;
import com.power.mall.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/9/14 6:29
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {

    @Autowired
    private UmsMenuMapper umsMenuMapper;

    @Override
    public List<UmsMenuTreeListDTO> treeList() {
        UmsMenuExample example = new UmsMenuExample();
        List<UmsMenu> umsMenus = umsMenuMapper.selectByExample(example);
        List<UmsMenuTreeListDTO> list = new ArrayList<>();
        for (UmsMenu umsMenu : umsMenus) {
            if (umsMenu.getLevel().equals(0)){
                UmsMenuTreeListDTO umsMenuTreeListDTO = new UmsMenuTreeListDTO();
                List<UmsMenu> children = new ArrayList<>();
                BeanUtils.copyProperties(umsMenu,umsMenuTreeListDTO);
                list.add(umsMenuTreeListDTO);
                for (UmsMenu menu : umsMenus) {
                    if (menu.getParentId().equals(umsMenuTreeListDTO.getId())){
                        children.add(menu);
                    }
                }
                umsMenuTreeListDTO.setChildren(children);
            }


        }
        return list;
    }
}
