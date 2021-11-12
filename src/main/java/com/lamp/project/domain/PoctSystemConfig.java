package com.lamp.project.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
* @author RollingStone-jianyijun
* @date 2020-05-12
*/

@Data
@TableName("sys_config")
public class PoctSystemConfig implements Serializable {

    /** 配置id */
    @TableId(value = "id",type= IdType.ASSIGN_ID)
    private String id;


    /** 字段名称 */
    private String menuName;


    /** 默认值 */
    private String value;


    /** 排序 */
    private Integer sort;


    /** 是否隐藏 */
    private Integer status;


    public void copy(PoctSystemConfig source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
