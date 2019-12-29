/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: UploadController.java
 * Author: lmy
 * Date: 19-12-7 下午4:18
 * Version: 1.0
 * LastModified: 19-12-7 下午4:17
 */

package com.leyou.upload.web;
/**
 * @Project leyou
 * @Package com.leyou.upload
 * @author lmy
 * @date 2019/12/7 16:15
 * @version V1.0
 */

import com.leyou.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lmy
 * @ClassName UploadController
 * @Description TODO
 * @date 2019/12/7 16:15
 **/
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadServiceImpl;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file){
        return ResponseEntity.ok(uploadServiceImpl.uploadImage(file));
    }

}
