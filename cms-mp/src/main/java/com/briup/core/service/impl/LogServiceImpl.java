package com.briup.core.service.impl;

import com.briup.core.bean.Log;
import com.briup.core.dao.LogDao;
import com.briup.core.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author briup
 * @since 2023-11-15
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, Log> implements ILogService {

}
