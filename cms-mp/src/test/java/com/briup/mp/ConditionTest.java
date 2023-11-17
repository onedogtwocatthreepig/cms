package com.briup.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.briup.mp.bean.User;
import com.briup.mp.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ConditionTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void eq() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper();
        lqw.eq(User::getName, "张大炮")
                .eq(User::getPassword, "123456");

        User user = userDao.selectOne(lqw);
        System.out.println("user = " + user);
    }

    @Test
    public void like() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper();
        // % o %
        lqw.like(User::getName, "o");
//        lqw.likeRight() o%
        List<User> users = userDao.selectList(lqw);
        users.forEach(System.out::println);
    }

    @Test
    public void order() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper();
        // SELECT id,name,password,age,tel FROM user
        // WHERE (id IN (1,2,3,4,5)) ORDER BY tel ASC,age DESC
        lqw.orderByAsc(User::getTel)
                .orderByDesc(User::getAge);
        lqw.in(User::getId, 1, 2, 3, 4, 5);

        List<User> users = userDao.selectList(lqw);
        users.forEach(System.out::println);
    }


}
