/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SpecificationController.java
 * Author: lmy
 * Date: 19-12-8 下午8:00
 * Version: 1.0
 * LastModified: 19-12-8 下午8:00
 */

package com.leyou.item.web;
/**
 * @Project leyou
 * @Package com.leyou.item.web
 * @author lmy
 * @date 2019/12/8 20:00
 * @version V1.0
 */

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author lmy
 * @ClassName SpecificationController
 * @Description TODO
 * @date 2019/12/8 20:00
 **/
@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationServiceImpl;


    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(specificationServiceImpl.queryGroupById(cid));
    }


    /**
     * 根据组cid和gid查询参数
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParamList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value= "searching", required = false) Boolean seaching
    ){

        return ResponseEntity.ok(specificationServiceImpl.queryParamList(gid, cid, seaching));
    }


    /**
     * 根据分类id查询规格组及组类参数信息
     * @param cid
     * @return
     */
    @GetMapping("group")
    ResponseEntity<List<SpecGroup>> queryGroupListByCid(@RequestParam("cid") Long cid){
        return ResponseEntity.ok(specificationServiceImpl.queryListByCid(cid));
    }


}
