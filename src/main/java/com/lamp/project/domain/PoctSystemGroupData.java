package com.lamp.project.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
* @author RollingStone-jianyijun
* @date 2020-05-12
*/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_group_data")
public class PoctSystemGroupData {

    /** 组合数据详情ID */
    @TableId
    private String id;


    /** 对应的数据名称 */
    private String groupName;


    /** 数据组对应的数据值（json数据） */
    private String value;


    /** 数据排序 */
    private Integer sort;


    /** 状态（1：开启；2：关闭；） */
    private Integer status;


    public void copy(PoctSystemGroupData source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
