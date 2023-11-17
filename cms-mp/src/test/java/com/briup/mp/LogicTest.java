package com.briup.mp;

import com.briup.mp.bean.User;
import com.briup.mp.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LogicTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void logic(){
        // 逻辑删除 update user set deleted = 1 where id = 6 and deleted = 0
        userDao.deleteById(6L);
    }

    @Test
    public void logicQuery(){
        // 逻辑查询 SELECT id,name,age,tel,deleted FROM user WHERE deleted=0
        List<User> users = userDao.selectList(null);
        System.out.println(users);
    }
}
