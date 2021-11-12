package com.lamp.project.service;

import com.lamp.allUtils.BaseService;
import com.lamp.project.domain.ProjectVo;
import com.lamp.project.domain.Projects;
import com.lamp.project.service.dto.ProjectsDto;
import com.lamp.project.service.dto.ProjectsQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author RollingStone-jianyijun
* @date 2020-08-22
*/
public interface ProjectsService extends BaseService<Projects> {

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String, Object>
    */

    Map<String, Object> queryAll(ProjectsQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ProjectsDto>
    */

    List<Projects> queryAll(ProjectsQueryCriteria criteria);

    /**
     * 根据当前用户ID查询关联的项目
     * @param uid 当前用户ID
     * @return
     */
    List<Projects> queryAllByUid(String uid);

    /**
     * 根据当前用户ID查询关联的项目
     * @param uid 当前用户ID
     * @param projectName 按项目名称查找
     * @return
     */
    List<Projects> findProjectsByParam(String uid, String projectName);

    /**
     * app根据项目ID修改项目信息
     * @param projectVo 条件参数
     * @return boolean
     */
    boolean updateByIdApp(ProjectVo projectVo);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ProjectsDto> all, HttpServletResponse response) throws IOException;

}
