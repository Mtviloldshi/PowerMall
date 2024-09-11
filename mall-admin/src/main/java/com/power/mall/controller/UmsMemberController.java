package com.power.mall.controller;

import com.power.mall.common.api.CommonResult;
import com.power.mall.dto.UserLoginDTO;
import com.power.mall.dto.UserRegisterDTO;
import com.power.mall.model.UmsMember;
import com.power.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name 会员管理
 * @Author SCH
 * @Date 2024年9月10日06:33:51
 */
@RestController
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService userMemberService;


    @PostMapping("/register")
    //@Validated 验证参数 NotEmpty?
    public CommonResult<Boolean> register(@RequestBody UserRegisterDTO registerDTO){
        Boolean register = userMemberService.register(registerDTO);
        System.out.println(register);
        return CommonResult.success(register);
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("/login")
    public CommonResult login(@RequestParam String username,
                              @RequestParam String password){
        String token = userMemberService.login(new UserLoginDTO(username,password));
        if (token == null || token.equals("用户名不存在")){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String,String> result = new HashMap<>();
        result.put("token",token);
        result.put("tokenHead",tokenHead);
        return CommonResult.success(result);
    }
    @GetMapping("/list")
    public CommonResult list(){
        List<UmsMember> list =userMemberService.list();
        return CommonResult.success(list);
    }
    //如何获取当前登录人信息？
    //什么时候保存登录人信息，保存到哪里？
    @GetMapping("/info")
    public CommonResult info(){
        UmsMember member =userMemberService.getCurrentMember();
        return CommonResult.success(member);
    }

}
