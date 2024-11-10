package com.power.mall.controller;

import com.power.mall.common.api.CommonPage;
import com.power.mall.common.api.CommonResult;
import com.power.mall.model.UmsAdmin;
import com.power.mall.model.UmsMember;
import com.power.mall.model.UmsMenu;
import com.power.mall.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/9/14 6:27
 */
@RestController
@RequestMapping("/menu")
public class UmsMenuController {

    @Autowired
    private UmsMenuService umsMenuService;

    @GetMapping("/treeList")
    public CommonResult treeList(){
        return CommonResult.success(umsMenuService.treeList());
    }

    @GetMapping("/list/{parentId}")
    public CommonResult list(@RequestParam(value = "keyword",required = false)String keyword,
                             @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                             @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                             @PathVariable @NotNull Long parentId
    ){
        List<UmsMenu> list =umsMenuService.list(keyword,pageSize,pageNum,parentId);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
