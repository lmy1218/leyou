/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: UploadService.java
 * Author: lmy
 * Date: 19-12-7 下午4:18
 * Version: 1.0
 * LastModified: 19-12-7 下午4:18
 */

package com.leyou.upload.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.upload.service
 * @date 2019/12/7 16:18
 */
public interface UploadService {
    String  uploadImage(@RequestParam("file")MultipartFile file);
}
