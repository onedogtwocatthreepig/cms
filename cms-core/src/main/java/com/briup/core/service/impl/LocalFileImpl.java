package com.briup.core.service.impl;

import com.briup.core.exception.ServiceException;
import com.briup.core.service.IFileService;
import com.briup.core.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author shaoyb
 * @program: FileUploadDemo
 * @description TODO
 * @create 2023/8/22 18:15
 **/
@Slf4j
public class LocalFileImpl implements IFileService {
    @Value("${upload.filePath}")
    private String parentPath;

    /**
     * @param file 待上传的文件
     * @return 上传成功后回显的url路径
     */
    public String upload(MultipartFile file)  {
        String filename = generateFilePath(file);
        System.out.println("filename = " + filename);
        log.info("上传到服务器后的文件名称:{}", filename);
        //创建输出文件对象
        File file2 = new File(parentPath, filename);
        //如果父目录不存在,先创建父目录
        File directory = file2.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //文件上传本地
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            throw new ServiceException(ResultCode.FILE_FAIULE);
        }
        //文件上传成功后,给返回的路径赋值,以存入数据库中,方便在浏览器中访问
        //注意nginx端口号,如果是默认80端口则可以省略，此处我们配置的nginx端口为89
        String url = "http://localhost:8989/static/images/" + filename;
        log.info("文件上传成功,文件地址:{}", url);

        return url;
    }

    /**
     * 随机生成文件在服务器上的名字, 使用UUID作为名称保证文件的唯一性(同时避免文件名乱码)
     *
     * @param file 源文件,用于获取文件 原名
     * @return 文件的新名
     */
    private String generateFilePath(MultipartFile file) {
        //通过上传日期,对文件进行分类
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
        String datePate = format.format(new Date());

        //获取文件名称
        String filename = file.getOriginalFilename();

        //给文件做唯一标识
        assert filename != null;
        return datePate + UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
    }
}
