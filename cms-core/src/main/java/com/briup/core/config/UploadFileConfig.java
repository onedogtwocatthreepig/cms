package com.briup.core.config;

import com.briup.core.service.IFileService;
import com.briup.core.service.impl.LocalFileImpl;
import com.briup.core.service.impl.QiniuFileImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: lucky(lgs)
 * @Date: 2023/11/16-11-16-11:24
 * @Description：com.briup.core.config
 */
//@ConditionalOnProperty(prefix = "upload")
@Configuration
public class UploadFileConfig {
    // 按照指定条件放到容器中
    // upload:
    //  method: local/qiniu
    @ConditionalOnProperty(prefix = "upload",name = "method",havingValue = "local")
    @Bean
    public IFileService localFileImpl(){
        return new LocalFileImpl();
    }

    @ConditionalOnProperty(prefix = "upload",name = "method",havingValue = "qiniu")
    @Bean
    public IFileService qiniuFileImpl(){
        return new QiniuFileImpl();
    }
}
