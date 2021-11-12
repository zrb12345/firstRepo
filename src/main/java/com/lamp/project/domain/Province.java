package com.lamp.project.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
* @author RollingStone-jianyijun
* @date 2020-08-18
*/
@Data
@TableName("base_province_unit")
public class Province implements Serializable {

    /** 行政单位ID */
    @TableId(type = IdType.INPUT)
    private String provinceId;


    /** 行政单位名称 */
    @NotBlank
    private String provinceName;


    /** 父ID（例如：01.01.09） */
    private String parentId;


    /** 行政单位全称 */
    private String fullName;


    /** 备注 */
    private String remark;


    /** 创建时间 */
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;


    /** 更新时间 */
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateTime;


    /** 是否启用 */
    private Boolean enabled;


    /** 删除标识 */
    @TableLogic
    private Integer isDel;


    public void copy(Province source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
