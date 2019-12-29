/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SpecificationApi.java
 * Author: lmy
 * Date: 19-12-12 下午7:38
 * Version: 1.0
 * LastModified: 19-12-12 下午7:38
 */

package com.leyou.item.api;
/**
 * @Project leyou
 * @Package com.leyou.item.api
 * @author lmy
 * @date 2019/12/12 19:38
 * @version V1.0
 */

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lmy
 * @ClassName SpecificationApi
 * @Description 规格参数接口
 * @date 2019/12/12 19:38
 **/

public interface SpecificationApi {

    @GetMapping("spec/params")
    List<SpecParam> queryParamList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value= "searching", required = false) Boolean seaching
    );


    @GetMapping("spec/group")
    List<SpecGroup> queryGroupListByCid(@RequestParam("cid") Long cid);


    @GetMapping("spec/groups/{cid}")
    List<SpecGroup> queryGroupByCid(@PathVariable("cid") Long cid);


}
