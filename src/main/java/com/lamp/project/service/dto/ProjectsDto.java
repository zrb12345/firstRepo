package com.lamp.project.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author RollingStone-jianyijun
* @date 2020-08-22
*/
@Data
public class ProjectsDto implements Serializable {

    /** 项目信息ID */
    private String projectId;

    /** 行政单位ID */
    private String provinceId;

    private ProvinceDto province;

    /** 客户ID */
    private String customerId;

    /** 项目名称 */
    private String projectName;

    /** 地址 */
    private String projectAddress;

    /** 行业类别 */
    private String projectType;

    /** 负责人 */
    private String contactPerson;

    /** 联系电话 */
    private String contactPhone;

    /** 经度 */
    private Float xPoint;

    /** 纬度 */
    private Float yPoint;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 是否启用 */
    private Boolean enabled;

    /** 删除标识 */
    private Integer isDel;

    public String getId(){
        return projectId;
    }

    public String getLabel() {
        return projectName;
    }
}
