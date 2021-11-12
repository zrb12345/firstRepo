package com.lamp.device.service.dto;

import com.lamp.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
* @author RollingStone-zhourb
* @date 2021-09-09
*/
@Data
public class DeviceQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String  devAlias;

    @Query
    private String gatewayId;

    @Query
    private Boolean enabled;

    /**
     * 多字段模糊搜索，仅支持String类型字段，多个用逗号隔开, 如@Query(blurry = "email,username")
     */
    @Query(blurry = "devAlias,devId")
    private String blurry;

    @Query
    private String onlineStatus;

    @Query
    private String projectId;

    @Query
    private String areaId;

    @Query(type = Query.Type.INNER_LIKE)
    private String devId;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
