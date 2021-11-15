package com.lamp.project.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author RollingStone-jianyijun
* @date 2020-08-22
*/
@Data
@TableName("base_project")
public class  Projects implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String projectId;
    @NotNull
    private String provinceId;
    @TableField(exist = false)
    private Province province;
    @NotNull(message = "客户ID不能为空")
    private String customerId;
    private String projectName;
    private String projectAddress;
    private String projectType;
    private String contactPerson;
    private String contactPhone;
    private Float xPoint;
    private Float yPoint;
    private String remark;
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;
    private Boolean enabled;
    @TableLogic
    private Integer isDel;


//    public void copy(Projects source) {
//        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
//    }
}