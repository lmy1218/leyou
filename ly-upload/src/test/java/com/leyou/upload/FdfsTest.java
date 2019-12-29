/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: FdfsTest.java
 * Author: lmy
 * Date: 19-12-8 上午10:54
 * Version: 1.0
 * LastModified: 19-12-8 上午10:54
 */

package com.leyou.upload;
/**
 * @Project leyou
 * @Package com.leyou.upload
 * @author lmy
 * @date 2019/12/8 10:54
 * @version V1.0
 */

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author lmy
 * @ClassName FdfsTest
 * @Description 图片上传测试类
 * @date 2019/12/8 10:54
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class FdfsTest {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;


    @Test
    public void testupload() throws FileNotFoundException {
        File file = new File("D:\\images\\abc.jpg");
        StorePath storePath = this.storageClient.uploadFile(
                new FileInputStream(file), file.length(), "jpg", null
        );

        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());
    }

    @Test
    public void testuploadAndCreateThumd() throws FileNotFoundException {
        File file = new File("D:\\images\\f.jpg");
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(
                new FileInputStream(file), file.length(), "jpg", null
        );

        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());
        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(path);
    }



}
