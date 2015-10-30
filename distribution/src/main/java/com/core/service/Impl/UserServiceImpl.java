package com.core.service.Impl;


import com.core.dao.SysUserMapper;
import com.core.model.SysUser;
import com.core.model.SysUserExample;
import com.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by core on 15/10/29.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser getUser(String name, String pwd) {
        SysUserExample example=new SysUserExample();
        example.createCriteria().andUserNameEqualTo(name).andLoginPwdEqualTo(pwd);
        return userMapper.selectByExample(example)==null?null:userMapper.selectByExample(example).get(0);
    }
    
}
