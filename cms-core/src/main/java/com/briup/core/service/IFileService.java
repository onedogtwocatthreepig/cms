package com.briup.core.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: lucky(lgs)
 * @Date: 2023/11/16-11-16-11:29
 * @Description：com.briup.core.service
 */
public interface IFileService {
    String upload(MultipartFile file);

}
