package com.lamp.project.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
* @author RollingStone-jianyijun
* @date 2020-08-18
*/
@Data
public class ProvinceDto implements Serializable {

    /** 行政单位ID */
    /** 防止精度丢失 */
    @JsonSerialize(using= ToStringSerializer.class)
    private String provinceId;

    /** 行政单位名称 */
    private String provinceName;

    /** 父ID（例如：01.01.09） */
    private String parentId;

    /** 行政单位全称 */
    private String fullName;

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

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ProvinceDto> children;

    public void addChild(ProvinceDto child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public String getId(){ return provinceId; }

    public String getLabel() {
        return provinceName;
    }
}
