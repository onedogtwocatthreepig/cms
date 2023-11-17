package com.briup.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.briup.mp.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/11/14-11-14-15:05
 * @Description：com.briup.mp.dao
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
