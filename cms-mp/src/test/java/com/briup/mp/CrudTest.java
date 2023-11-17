package com.briup.mp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.mp.bean.User;
import com.briup.mp.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CrudTest {
    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        // 不带条件查询(查询所有)  user表的数据
        List<User> users = userDao.selectList(null);
        System.out.println("users = " + users);
    }

    @Test
    void testSave() {
        User user = new User();
        user.setName("杰普");
        user.setPassword("briup");
        user.setAge(12);
        user.setTel("13900010002");
        userDao.insert(user);
    }

    @Test
    void testDelete() {
        userDao.deleteById(1724337866345660418L);
    }

    @Test
    void testUpdate() { // 动态sql
        User user = new User();
        //提供的字段才会修改，不提供不修改
        user.setId(1L);
        user.setName("Tom8881222221");
        user.setPassword("tom888222222");
        // UPDATE user SET name='Tom8881111', pwd='tom888111', version=2 WHERE id=1 AND version=1 AND deleted=0
        user.setVersion(2);  // 更新需要携带版本(乐观锁)
        userDao.updateById(user);
    }

    @Test
    public void queryById(){
        User user = userDao.selectById(1L);
        System.out.println("user = " + user);
    }

    // 分页查询
    @Test
    public void queryByPage(){
        // 分页和条件
        IPage<User> page = new Page<>(1,5);
        // 将查询到的数据 自动 封装到了 page中
        userDao.selectPage(page,null);

        System.out.println("当前页码值：" + page.getCurrent());
        System.out.println("每页显示数：" + page.getSize());
        System.out.println("总页数：" + page.getPages());

        System.out.println("总条数：" + page.getTotal());
        System.out.println("当前页数据：" + page.getRecords());
    }


}
