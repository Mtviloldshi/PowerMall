package com.power.mall.service.impl;

import com.power.mall.dto.UserLoginDTO;
import com.power.mall.dto.UserRegisterDTO;
import com.power.mall.mapper.UserMemberMapper;
import com.power.mall.model.UmsMember;
import com.power.mall.service.UmsMemberService;
import com.power.mall.security.util.JwtTokenUtil;
import com.power.mall.model.UmsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/6/27 12:12
 */
@Service
public class UmsMemberImpl implements UmsMemberService {

    @Autowired
    private UserMemberMapper userMemberMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Boolean register(UserRegisterDTO dto) {
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(dto.getUsername());
        umsMember.setPassword(dto.getPassword());
        int insert = userMemberMapper.insert(umsMember);
        System.out.println(
                "用户名 :"+ dto.getUsername()+
                "密码 :"+ dto.getPassword()+
                "注册是否成功 :"+ insert);
        return true;
    }

    @Override
    public String login(UserLoginDTO loginDTO) {
        if (loginDTO.getPassword().isEmpty() || loginDTO.getUsername().isEmpty()) {
            return "用户名或密码不能为空";
        }
        UmsExample umsExample = new UmsExample();
        umsExample.createCriteria().andUsernameEqualTo(loginDTO.getUsername());
        List<UmsMember> umsMembers = userMemberMapper.selectByExample(umsExample);
        //用户名不能为空吗，且用户名不能重复出现
        if (umsMembers == null || umsMembers.isEmpty()) {
            return "用户名不存在";
        } else if (umsMembers.size()>1){
            return "有多个相同用户名存在，请联系管理员";
        } else if (umsMembers.get(0).getPassword().equals(loginDTO.getPassword())){
            //用户一定需要设置权限吗？
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(umsMembers.get(0),null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return jwtTokenUtil.generateToken(umsMembers.get(0));
        }else{
            return "密码错误登录失败";
        }


    }

    @Override
    public List<UmsMember> list() {
        List<UmsMember> list = userMemberMapper.list();
        return list;
    }

    @Override
    public UmsMember getCurrentMember() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        UmsMember principal = (UmsMember) auth.getPrincipal();
        return principal;
    }
}
