package com.briup.core.web.controller;

import com.briup.core.service.IFileService;
import com.briup.core.service.impl.LocalFileImpl;
import com.briup.core.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/11/15-11-15-15:15
 * @Description：com.briup.core.web.controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    private final IFileService fileService;

    @PostMapping("/upload")
    public Result upload(@RequestPart MultipartFile file) {
        // 上传 本地上传/七牛云上传/oss
        String url = fileService.upload(file);
        return Result.success(url);
    }

}
