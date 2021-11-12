package com.lamp.project.service.mapper;

import com.lamp.allUtils.CoreMapper;
import com.lamp.project.domain.ProjectVo;
import com.lamp.project.domain.Projects;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author RollingStone-jianyijun
* @date 2020-08-22
*/
@Repository
public interface ProjectsMapper extends CoreMapper<Projects> {

    @Select("SELECT t.* FROM base_project t " +
            "LEFT JOIN sys_user_projects uh ON uh.project_id = t.project_id " +
            "WHERE uh.user_id = #{uid} and t.enabled= true and t.is_del=0")
    List<Projects> findProjectsByUid(@Param("uid") String uid);

    @Select("SELECT t.* FROM base_project t " +
            "LEFT JOIN sys_user_projects uh ON uh.project_id = t.project_id " +
            "WHERE uh.user_id = #{uid} and t.project_name LIKE concat('%', #{projectName}, '%') and t.enabled= true and t.is_del=0")
    List<Projects> findProjectsByParam(@Param("uid") String uid, @Param("projectName") String projectName);

    @Update("update base_project set project_name = #{projectName},project_address = #{projectAddress}," +
            "province_id=#{provinceId}, update_time = now() where project_id = #{projectId}")
    boolean updateByIdApp(ProjectVo projectVo);


}
