/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: UploadServiceImpl.java
 * Author: lmy
 * Date: 19-12-7 下午4:19
 * Version: 1.0
 * LastModified: 19-12-7 下午4:19
 */

package com.leyou.upload.service.impl;
/**
 * @Project leyou
 * @Package com.leyou.upload.service.impl
 * @author lmy
 * @date 2019/12/7 16:19
 * @version V1.0
 */

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import com.leyou.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author lmy
 * @ClassName UploadServiceImpl
 * @Description TODO
 * @date 2019/12/7 16:19
 **/
@Slf4j
@Service
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements UploadService {

    //文件类型
//    private static final List<String> ALLOWTYPE = Arrays.asList("image/png","image/jpeg","image/jpg");

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadProperties prop;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型
            String contentType = file.getContentType();
            if(!prop.getAllowTypes().contains(contentType)){
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(bufferedImage == null){
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //保存文件
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extension, null);

            //返回路径
            return prop.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            log.error("[文件上传] 上传图片失败", e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
    }
}
