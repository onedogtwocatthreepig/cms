package com.briup.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.mp.bean.User;
import com.briup.mp.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class WrapperTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void query() {
        // 条件封装
        QueryWrapper<User> qw = new QueryWrapper<>();
        // less then  grater then
        // where age < 15
//        qw.lt("age",15);
        qw.le("age", 15); // <=
        List<User> users = userDao.selectList(qw);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void queryLambda() {
        // 条件封装
        QueryWrapper<User> qw = new QueryWrapper<>();
        // User类型 -> 具体类型 类型::静态方法
        // 类型::实例方法  方法的第一个参数是对象
        // user.getAge();
        qw.lambda().le(User::getAge, 15);
//       qw.lambda().le(new SFunction<User, Integer>() {
//           @Override
//           public Integer apply(User user) {
//               return null;
//           }
//       })
        List<User> users = userDao.selectList(qw);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }


    @Test
    public void lambadaQuery2() {
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.le(User::getId,5);
        List<User> users = userDao.selectList(Wrappers.<User>lambdaQuery().le(User::getId, 5));
        // 类::实例方法
        users.forEach(System.out::println);
    }


    @Test
    public void testMuliCondition() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // < 15 >10
//        wrapper.lt(User::getAge,15);
//        wrapper.gt(User::getAge,10);
        wrapper.lt(User::getAge, 20)
                .gt(User::getId, 5);
        List<User> users = userDao.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testMuliCondition2() {
        List<User> users = userDao.selectList(
                Wrappers.lambdaQuery(User.class)
                        .le(User::getAge, 10)
                        .or()
                        .ge(User::getAge, 15)
        );
        users.forEach(System.out::println);
    }


    @Test
    public void testMuliConditionNotNull() {
        Integer age= null;
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt( age != null, User::getAge, 15);
        List<User> users = userDao.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSomeColumn() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // 查询指定字段
        wrapper.select(User::getId,User::getName);
        List<User> users = userDao.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testMap() {
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.se
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // select count(id) as count,name from user;
        queryWrapper.select("count(id) as count,password");
        // select列如果没有被组函数修饰,必须放在groupby后面
        queryWrapper.groupBy("password");
//        List<User> users = userDao.selectList(queryWrapper);
//        System.out.println("users = " + users);
        List<Map<String, Object>> list = userDao.selectMaps(queryWrapper);
        list.forEach(map -> {
            map.forEach((k,v) -> System.out.println(k+"="+v));
            System.out.println("---------------");
        });
    }

}
