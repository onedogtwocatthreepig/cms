package com.briup.core.web.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.core.bean.Slideshow;
import com.briup.core.service.ISlideshowService;
import com.briup.core.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 轮播图 前端控制器
 * </p>
 *
 * @author briup
 * @since 2023-11-15
 */
@Api(tags = "轮播图")
@RestController
@RequiredArgsConstructor
@RequestMapping("/slideshow")
public class SlideshowController {
    private final ISlideshowService slideshowService;

//    @ApiOperation("添加或修改")
//    @PostMapping("/saveOrUpdate")
//    // @Valid hibernate注解校验生效
//    // BindingResult 存储了 违背了校验注解的信息
//    public Result saveOrUpdate(@RequestBody @Valid Slideshow slideshow, BindingResult result) {
//        List<ObjectError> allErrors = result.getAllErrors();
//        for (ObjectError allError : allErrors) {
//            String defaultMessage = allError.getDefaultMessage();
//            // defaultMessage = url不能为空
//            System.out.println("defaultMessage = " + defaultMessage);
//        }
//        slideshowService.saveOrUpdateMy(slideshow);
//        return Result.success();
//    }


    @ApiOperation("添加或修改")
    @PostMapping("/saveOrUpdate")
    // @Valid hibernate注解校验生效
    // BindingResult 存储了 违背了校验注解的信息
    public Result saveOrUpdate(@RequestBody @Valid Slideshow slideshow) {
        slideshowService.saveOrUpdateMy(slideshow);
        return Result.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/deleteByBatch/{ids}")
    public Result deleteByBatch(@PathVariable List<Integer> ids) {
//        slideshowService.removeById(id);
        slideshowService.removeByIdMe(ids);
        return Result.success();
    }

//    @ApiOperation("批量删除")
//    @PostMapping("/deleteBatch")
//    public Result deleteBatch(@RequestBody List<Integer> ids) {
//        slideshowService.removeByIds(ids);
//        return Result.success();
//    }

    @ApiOperation("查询可用的轮播图")
    @GetMapping("/queryAllEnable")
    public Result findAll() {
        //  new LambdaQueryWrapper<>(entityClass)
        List<Slideshow> list = slideshowService.list(
                Wrappers.lambdaQuery(Slideshow.class)
                        .eq(Slideshow::getStatus, "启用")
                        .orderByDesc(Slideshow::getUploadTime)
        );
        return Result.success(list);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/queryById/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(slideshowService.getById(id));
    }

    @ApiOperation("分页条件查询")
    @GetMapping("/query")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           String status,
                           String desc) {
        // 分页
        Page page = new Page<>(pageNum, pageSize);
        // 条件查询数据 并将数据 封装到 page对象中
        slideshowService.page(page, Wrappers.lambdaQuery(Slideshow.class)
                .eq(StringUtils.isNotBlank(status),Slideshow::getStatus,status)
                .like(StringUtils.isNotBlank(desc),Slideshow::getDescription,desc)
        );
        return Result.success(page);
    }

}

