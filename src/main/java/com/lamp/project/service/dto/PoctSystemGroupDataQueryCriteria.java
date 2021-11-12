package com.lamp.project.service.dto;

import com.lamp.annotation.Query;
import lombok.Data;

/**
* @author RollingStone-jianyijun
* @date 2020-05-12
*/
@Data
public class PoctSystemGroupDataQueryCriteria {

    // 精确
    @Query
    private String groupName;
}
