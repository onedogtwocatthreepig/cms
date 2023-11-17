package com.briup.core.service.impl;

import com.briup.core.exception.ServiceException;
import com.briup.core.service.IFileService;
import com.briup.core.util.ResultCode;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: lucky(lgs)
 * @Date: 2023/11/16-11-16-11:29
 * @Description：com.briup.core.service.impl
 */
@Slf4j
public class QiniuFileImpl implements IFileService {
    @Value("${upload.oss.accessKey}")
    private String accessKey;
    @Value("${upload.oss.secretKey}")
    private String secretKey;
    @Value("${upload.oss.bucket}")
    private String bucket;
    @Value("${upload.oss.baseUrl}")
    private String baseUrl;

    public String upload(MultipartFile file){
        log.info("文件上传到七牛云OOS:{}",file.getOriginalFilename());
        //构造一个带指定 Region 对象的配置类(根据七牛云服务器地区进行设置,
        //这里autoRegion会自动匹配相应地区七牛云服务)
        Configuration configuration = new Configuration(Region.autoRegion());
        //将配置传入UploadManager
        UploadManager uploadManager = new UploadManager(configuration);
        //验证AK与SK,AK与SK从配置类中获取
        Auth auth = Auth.create(accessKey, secretKey);

        //指定桶 从配置类中获取
        String upToken = auth.uploadToken(bucket);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String fileName = generateFilePath(file);

        try {
            //上传文件
            Response response = uploadManager.put(file.getInputStream(), fileName,
                    upToken, null, null);

            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),
                    DefaultPutRet.class);
        }catch (IOException e){
            throw new ServiceException(ResultCode.FILE_FAIULE);
        }
        log.info("文件上传成功,文件地址:{}", baseUrl + fileName);

        return baseUrl + fileName;
    }


    /**
     * 该方法用于生成有效文件路径
     * 路径格式统一为: yyyy/MM/dd/随机UUID.文件格式
     *
     * @param file 源文件,用于获取文件 原名
     * @return 文件的新名
     */
    private String generateFilePath(MultipartFile file) {
        //1.根据日期生成路径字符串   2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());

        //2.获取有效 uuid
        //	注意：uuid默认为 xxx-xxx-xxx...，此处我们要把-去掉
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        //3.获取文件的后缀名：test.png -> .png
        //获取文件名称
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);

        //给文件做唯一标识
        assert fileName != null;

        //4.拼接得到有效文件名，然后返回
        String filePath = new StringBuilder().append(datePath).append(uuid).append(fileType).toString();
        return filePath;
    }
}
