package com.power.mall.controller;

import com.power.mall.common.api.CommonPage;
import com.power.mall.common.api.CommonResult;
import com.power.mall.model.UmsRole;
import com.power.mall.service.UmsAdminService;
import com.power.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/9/13 18:07
 */
@RestController
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;
    @Autowired
    private UmsAdminService adminService;
    @GetMapping("/listAll")
    public CommonResult listAll(){
        return CommonResult.success(roleService.listAll());
    }
    @GetMapping("/list")
    public CommonResult list(@RequestParam(value = "keyword",required = false) String keyword,
                             @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                             @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum
                             ){
        List<UmsRole> list = roleService.list(keyword,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @GetMapping("/listMenu/{id}")
    public CommonResult listMenu(@PathVariable Long id){
        return CommonResult.success(roleService.getMenuList(id));
    }
}
