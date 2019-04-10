package com.springmvc.service.Impl;

import com.springmvc.entity.User;
import com.springmvc.mapper.UserMapper;
import com.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void save(User user) {
            userMapper.add(user);
    }
}
