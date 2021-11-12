package com.lamp.project.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author RollingStone-wtx
 * @date 2021-09-22
 */
@Data
public class ProjectVo implements Serializable {
    /**
     *项目信息ID
     */
    @NotBlank(message = "项目ID不能为空")
    @ApiModelProperty("项目ID")
    private String projectId;

    /**
     * 行政单位ID
     */
    @NotBlank(message = "行政单位ID不能为空")
    @ApiModelProperty("行政单位ID")
    private String provinceId;

    /**
     * 项目名称
     */
    @ApiModelProperty("项目名称")
    private String projectName;

    /**
     * 项目地址
     */
    @ApiModelProperty("项目地址")
    private String projectAddress;

    /**
     * 是否启用
     */
    @ApiModelProperty("是否启用")
    private Boolean enabled;



    public void copy(ProjectVo source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
