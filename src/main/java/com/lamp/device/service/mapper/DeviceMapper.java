package com.lamp.device.service.mapper;

import com.lamp.device.entity.Device;
import com.lamp.allUtils.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author RollingStone-zhourb
* @date 2021-09-09
*/
@Mapper
public interface DeviceMapper  extends CoreMapper<Device> {
    @Select("SELECT t.* FROM base_device t WHERE t.project_id = #{projectId} and t.enabled=1 ")
    List<Device> findDevicesByProjectId(@Param("projectId") String projectId);

    @Select("SELECT bd.*,dp.dev_model_id,dp.prod_name,dp.color,dp.icon,dp.remark as productRemark from base_device bd LEFT JOIN dev_product dp on dp.prod_event_id = bd.prod_event_id where bd.mac = #{mac} and bd.is_del = 0")
    Map<String, Object> getDevAndProductInfo(@Param("mac") String mac);

    @Select("SELECT mc.*,tc.dev_type,tc.service,tc.service_name,tc.property,tc.property_name,tc.property_type,tc.siid,tc.ciid,tc.method,tc.unit,tc.unit_type," +
            "tc.check_way,tc.default_value,tc.is_request,tc.remark as propertyRemark from dev_model_characher mc " +
            "LEFT JOIN dev_type_characher tc on tc.dev_type_event_id = mc.dev_type_event_id where dev_model_id = #{devModelId} and mc.is_del=0" )
    List<Map<String, Object>> getModelAndCharacherInfo(@Param("devModelId") String devModelId);

}
