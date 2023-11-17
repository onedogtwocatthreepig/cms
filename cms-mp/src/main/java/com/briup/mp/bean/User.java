package com.briup.mp.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("user")  // 类对应的表名
public class User {
    // auto 自增
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    // 属性对应的列配置   password  -> pwd
    @TableField(value = "pwd",select = false)
    private String password;
    private Integer age;
    private String tel;

    // 如果该列是数据库没有的,就会报错
    @TableField(exist = false) // 指明没有该列 就不会当作查询列
    private String roleId;

    // 是否删除
    @TableLogic
    private Integer deleted;


    @Version
    private Integer version;  // 版本(乐观锁)

}