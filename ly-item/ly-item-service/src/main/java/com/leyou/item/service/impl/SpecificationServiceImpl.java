/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SpecificationServiceImpl.java
 * Author: lmy
 * Date: 19-12-8 下午7:58
 * Version: 1.0
 * LastModified: 19-12-8 下午7:58
 */

package com.leyou.item.service.impl;
/**
 * @Project leyou
 * @Package com.leyou.item.service.impl
 * @author lmy
 * @date 2019/12/8 19:58
 * @version V1.0
 */

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmy
 * @ClassName SpecificationServiceImpl
 * @Description 规格参数处理
 * @date 2019/12/8 19:58
 **/
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;


    /**
     * 根据分类ID查询规格组
     * @param cid
     * @return
     */
    @Override
    public List<SpecGroup> queryGroupById(Long cid) {
        SpecGroup group = new SpecGroup();
        group.setCid(cid);
        List<SpecGroup> list = specGroupMapper.select(group);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOND);
        }
        return list;
    }

    @Override
    public List<SpecParam> queryParamList(Long gid, Long cid, Boolean seaching) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setSearching(seaching);
        List<SpecParam> list = specParamMapper.select(param);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOND);
        }
        return list;
    }

    @Override
    public List<SpecGroup> queryListByCid(Long cid) {
        //查询规格组
        List<SpecGroup> specGroups = queryGroupById(cid);
        //查询组内信息
        List<SpecParam> specParams = queryParamList(null, cid, null);
        Map<Long, List<SpecParam>> paramMap = new HashMap<>();
        for (SpecParam specParam : specParams) {
            if(!paramMap.containsKey(specParam.getGroupId())){
                paramMap.put(specParam.getGroupId(), new ArrayList<>());
            }
            paramMap.get(specParam.getGroupId()).add(specParam);
        }

        for (SpecGroup specGroup : specGroups) {
            specGroup.setParams(paramMap.get(specGroup.getId()));
        }


        return specGroups;
    }
}
