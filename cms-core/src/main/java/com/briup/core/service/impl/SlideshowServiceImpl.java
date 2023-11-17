package com.briup.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.briup.core.bean.Slideshow;
import com.briup.core.dao.SlideshowDao;
import com.briup.core.exception.ServiceException;
import com.briup.core.service.ISlideshowService;
import com.briup.core.util.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 轮播图 服务实现类
 * </p>
 *
 * @author briup
 * @since 2023-11-15
 *
 * extends ServiceImpl<SlideshowDao, Slideshow>  BaseMapper
 */
@Service
public class SlideshowServiceImpl extends ServiceImpl<SlideshowDao, Slideshow> implements ISlideshowService {

    // TODO 如何判断图片是否相同 不能简单通过url
    // TODO 逻辑删除怎么保证数据唯一
    @Transactional
    @Override
    public void saveOrUpdateMy(Slideshow slideshow) {
        // https://blog.csdn.net/qq_66345100/article/details/129322325
        boolean flag = this.saveOrUpdate(slideshow);
        if(!flag){
            throw new ServiceException(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
        }
        // int i = 1/0; 测试事务
    }

    @Transactional
    @Override
    public void removeByIdMe(List<Integer> ids) {
        // 逻辑删除 0 -> 1
        boolean flag = this.removeByIds(ids);
        if(!flag){
            throw new ServiceException(ResultCode.DATA_NONE);
        }
    }
}
