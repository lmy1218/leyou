/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: FastClientImporter.java
 * Author: lmy
 * Date: 19-12-8 上午10:47
 * Version: 1.0
 * LastModified: 19-12-8 上午10:47
 */

package com.leyou.upload.config;
/**
 * @Project leyou
 * @Package com.leyou.upload.config
 * @author lmy
 * @date 2019/12/8 10:47
 * @version V1.0
 */

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @author lmy
 * @ClassName FastClientImporter
 * @Description TODO
 * @date 2019/12/8 10:47
 **/
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastClientImporter {
}
