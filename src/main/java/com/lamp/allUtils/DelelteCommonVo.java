package com.lamp.allUtils;

import com.lamp.annotation.Query;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author RollingStone-jianyijun
 * @date 2020-08-22
 */
@Data
public class DelelteCommonVo {

    @Query
    @ApiModelProperty(value = "多个ID用逗号隔开")
    private String idStrings;

    @Query
    @ApiModelProperty(value = "多个ID,数组")
    private String[] ids;


}
