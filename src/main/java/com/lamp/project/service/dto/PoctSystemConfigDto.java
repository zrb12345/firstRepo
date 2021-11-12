package com.lamp.project.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
* @author RollingStone-jianyijun
* @date 2020-05-12
*/
@Data
public class PoctSystemConfigDto implements Serializable {

    /** 配置id */
    private Integer id;

    /** 字段名称 */
    private String menuName;

    /** 默认值 */
    private String value;

    /** 排序 */
    private Integer sort;

    /** 是否隐藏 */
    private Integer status;
}
