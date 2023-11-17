package com.briup.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.briup.core.bean.Slideshow;

import java.util.List;

/**
 * <p>
 * 轮播图 服务类
 * </p>
 *
 * @author briup
 * @since 2023-11-15
 */
public interface ISlideshowService extends IService<Slideshow> {

    void saveOrUpdateMy(Slideshow slideshow);

    void removeByIdMe(List<Integer> ids);

}
