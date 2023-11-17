package com.briup.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.briup.mp.bean.User;
import com.briup.mp.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BatchTest {
    @Autowired
    private UserDao userDao;
    
    @Test
    public void batchDelete(){
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
//        userDao.deleteBatchIds(ids);

        List<User> users = userDao.selectBatchIds(ids);
        System.out.println("users = " + users);
    }

}
