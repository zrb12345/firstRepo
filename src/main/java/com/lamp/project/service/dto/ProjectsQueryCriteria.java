package com.lamp.project.service.dto;

import com.lamp.annotation.Query;
import lombok.Data;

import java.util.Set;

/**
* @author RollingStone-jianyijun
* @date 2020-08-22
*/
@Data
public class ProjectsQueryCriteria {

    @Query(type = Query.Type.IN, propName="project_Id")
    private Set<String> projectIds;

    @Query
    private Boolean enabled;

    @Query
    private Integer isDel;

    @Query
    private String provinceId;

    @Query(type = Query.Type.INNER_LIKE)
    private String projectName;

    @Query
    private String customerId;

    @Query
    private String uid;

}

