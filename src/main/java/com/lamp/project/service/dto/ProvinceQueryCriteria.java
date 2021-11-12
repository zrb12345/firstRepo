package com.lamp.project.service.dto;

import com.lamp.annotation.Query;
import lombok.Data;

/**
* @author RollingStone-jianyijun
* @date 2020-08-18
*/
@Data
public class ProvinceQueryCriteria {

    @Query
    private Boolean enabled;
}
