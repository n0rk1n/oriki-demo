package cn.oriki.cxf.service.impl;

import cn.oriki.cxf.domain.User;
import cn.oriki.cxf.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        System.out.println("保存：" + user);

    }

    @Override
    public void update(User user) {
        System.out.println("更新：" + user);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("删除：" + id);

    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Rose", "666"));
        userList.add(new User(2, "Jack", "666"));
        userList.add(new User(3, "小明", "666"));
        userList.add(new User(4, "小红", "666"));
        return userList;
    }

    @Override
    public User findById(Integer id) {
        System.out.println("查询的：" + id);
        return new User(1, "Rose", "666");
    }

}
