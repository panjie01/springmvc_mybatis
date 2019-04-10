package com.springmvc.mapper;

import com.springmvc.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    public void add(User user);

    public User queryById(int id);

    User queryByKeyword(Map<String,Object> map);

    List<User> queryByAges(List<Integer> list);
}
