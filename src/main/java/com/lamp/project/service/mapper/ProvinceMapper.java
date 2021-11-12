package com.lamp.project.service.mapper;

import com.lamp.allUtils.CoreMapper;
import com.lamp.project.domain.Province;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
* @author RollingStone-jianyijun
* @date 2020-08-18
*/
@Repository
public interface ProvinceMapper extends CoreMapper<Province> {

    @Select("SELECT count(1) FROM base_province_unit t WHERE t.parent_id = #{parentId} ")
    int countProvinceNumberByPid(@Param("parentId") String parentId);

}
