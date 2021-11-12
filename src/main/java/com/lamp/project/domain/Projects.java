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
public class Projects implements Serializable {

    /**
     * 项目信息ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String projectId;

    /**
     * 行政单位ID
     */
    @NotNull
    private String provinceId;

    @TableField(exist = false)
    private Province province;

    /**
     * 客户ID
     */
    @NotNull(message = "客户ID不能为空")
    private String customerId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目地址
     */
    private String projectAddress;

    /**
     * 行业类别
     */
    private String projectType;

    /**
     * 负责人
     */
    private String contactPerson;


    /**
     * 联系电话
     */
    private String contactPhone;


    /**
     * 经度
     */
    private Float xPoint;


    /**
     * 纬度
     */
    private Float yPoint;


    /**
     * 备注
     */
    private String remark;


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;


    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;


    /**
     * 是否启用
     */
    private Boolean enabled;


    /**
     * 删除标识
     */
    @TableLogic
    private Integer isDel;


    public void copy(Projects source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}