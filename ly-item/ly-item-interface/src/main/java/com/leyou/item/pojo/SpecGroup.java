/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SpecGroup.java
 * Author: lmy
 * Date: 19-12-8 下午7:51
 * Version: 1.0
 * LastModified: 19-12-8 下午7:51
 */

package com.leyou.item.pojo;
/**
 * @Project leyou
 * @Package com.leyou.item.pojo
 * @author lmy
 * @date 2019/12/8 19:51
 * @version V1.0
 */

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author lmy
 * @ClassName SpecGroup
 * @Description 规格组
 * @date 2019/12/8 19:51
 **/
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long cid;
    private String name;
    @Transient
    private List<SpecParam> params;
}
