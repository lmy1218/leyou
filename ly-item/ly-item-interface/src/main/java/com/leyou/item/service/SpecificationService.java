/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SpecificationService.java
 * Author: lmy
 * Date: 19-12-8 下午7:58
 * Version: 1.0
 * LastModified: 19-12-8 下午7:58
 */

package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.item.service
 * @date 2019/12/8 19:58
 */
public interface SpecificationService {
    List<SpecGroup> queryGroupById(Long cid);

    List<SpecParam> queryParamList(Long gid, Long cid, Boolean seaching);

    List<SpecGroup> queryListByCid(Long cid);
}
