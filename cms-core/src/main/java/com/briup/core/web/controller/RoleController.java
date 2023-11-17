package com.briup.core.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.briup.core.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import com.briup.core.service.IRoleService;
import com.briup.core.bean.Role;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author briup
 * @since 2023-11-15
 */
@Api(tags = "")
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final IRoleService roleService;

    @ApiOperation("添加或修改")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Role role) {
    roleService.saveOrUpdate(role);
        return Result.success();
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/deleteById")
    public Result deleteById(Integer id) {
    roleService.removeById(id);
        return Result.success();
    }

    @ApiOperation("批量删除")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
    roleService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation("基本查询")
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(roleService.list());
    }

    @ApiOperation("根据id查询")
    @GetMapping("/findOne")
    public Result findOne(Integer id) {
        return Result.success(roleService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

