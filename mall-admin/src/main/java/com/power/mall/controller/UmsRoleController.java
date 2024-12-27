package com.power.mall.controller;

import com.power.mall.common.api.CommonPage;
import com.power.mall.common.api.CommonResult;
import com.power.mall.model.UmsRole;
import com.power.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;

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

    /**
     * 获取后台用户所关联的资源
     * @param id
     * @return
     * Todo 写到分配资源了
     */
    @GetMapping("/listResource/{id}")
    public CommonResult listResource(@PathVariable Long id){
        return CommonResult.success(roleService.getListResource(id));
    }

}
