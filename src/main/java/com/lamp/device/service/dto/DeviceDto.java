package com.lamp.device.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author RollingStone-zhourb
* @date 2021-09-09
*/
@Data
public class DeviceDto implements Serializable {

    private String devId;

    public String getId(){ 
		return devId;
	}

    /** 客户ID */
    private String customerId;

    /** 项目ID */
    private String projectId;

    /** 区域ID */
    private String areaId;

    /** 网关ID */
    private String gatewayId;

    /** 产品ID(用来继承物模型设置) */
    private String prodEventId;

    /** 设备SN */
    private String devSn;

    /** 设备别名 */
    private String devAlias;

    /** 设备安装地址 */
    private String devAddr;

    /** 设备制造商ID */
    private String manu;

    /** 设备MAC地址 */
    private String mac;

    /** 协议类型（从字典中获取数据） */
    private String protocolType;

    /** 在线状态（从安字典中取数据） */
    private String onlineStatus;

    /** 排序号 */
    private Integer orderNum;

    /** 设备版本号 */
    private String devVersion;

    /** 备注 */
    private String remark;

    /** 最后控制时间 */
    private Timestamp lastControlTime;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 是否启用 */
    private Boolean enabled;

    /** 删除标识 */
    private Integer isDel;

    /** 区域名称*/
    private String areaName;

    /** 项目名称*/
    private String projectName;

    /** 产品名称 */
    private String prodName;

    /** 网关名称 */
    private String gatewayName;

    /** 客户名称 */
    private String CustomerName;

    /** 设备名称 */
    private String deviceName;

    /** 设备状态 */
    private String deviceStatus;
}
