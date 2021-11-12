package com.lamp.device.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author RollingStone-zhourb
 * @date 2021-11-11
 */
@Data
@TableName("base_device")
public class Device implements Serializable {
    /** 设备ID */
    @TableId
    private String devId;

    /** 设备类型 */

    private String devType;

    /** 设备别名 */
    private String devAlias;

    /** 删除标识 */
    @TableLogic
    private Integer isDel;
//    /** 项目ID */
//    @NotBlank(message = "项目ID不能为空")
//    private String projectId;

//    /** 区域名称 */
//    @TableField(exist = false)
//    private String areaName;



}
