package com.briup.core.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.briup.core.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import com.briup.core.service.ISubcommentService;
import com.briup.core.bean.Subcomment;

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
@RequestMapping("/subcomment")
public class SubcommentController {
    private final ISubcommentService subcommentService;

    @ApiOperation("添加或修改")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Subcomment subcomment) {
    subcommentService.saveOrUpdate(subcomment);
        return Result.success();
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/deleteById")
    public Result deleteById(Integer id) {
    subcommentService.removeById(id);
        return Result.success();
    }

    @ApiOperation("批量删除")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
    subcommentService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation("基本查询")
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(subcommentService.list());
    }

    @ApiOperation("根据id查询")
    @GetMapping("/findOne")
    public Result findOne(Integer id) {
        return Result.success(subcommentService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        QueryWrapper<Subcomment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(subcommentService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

