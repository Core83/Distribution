package com.core.service;

import com.core.model.SysUser;

/**
 * Created by core on 15/10/29.
 */
public interface IUserService {
    SysUser getUser(String name,String pwd);
}
