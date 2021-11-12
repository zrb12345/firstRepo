package com.lamp.project.service.impl;

import com.github.pagehelper.PageInfo;
import com.lamp.allUtils.FileUtil;
import com.lamp.allUtils.IGenerator;
import com.lamp.allUtils.QueryHelpPlus;
import com.lamp.allUtils.impl.BaseServiceImpl;
import com.lamp.project.domain.ProjectVo;
import com.lamp.project.domain.Projects;
import com.lamp.project.service.ProjectsService;
import com.lamp.project.service.ProvinceService;
import com.lamp.project.service.dto.ProjectsDto;
import com.lamp.project.service.dto.ProjectsQueryCriteria;
import com.lamp.project.service.mapper.ProjectsMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author RollingStone-jianyijun
* @date 2020-08-22
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "Projects")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProjectsServiceImpl extends BaseServiceImpl<ProjectsMapper, Projects> implements ProjectsService {
    @Autowired
    private  IGenerator generator;
    private final ProvinceService provinceService;
    private final ProjectsMapper projectsMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ProjectsQueryCriteria criteria, Pageable pageable) {
        getPage2(pageable);
        PageInfo<Projects> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ProjectsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<Projects> queryAll(ProjectsQueryCriteria criteria){
        List<Projects> projectsList = baseMapper.selectList(QueryHelpPlus.getPredicate(Projects.class, criteria));
        for (Projects project : projectsList) {
            project.setProvince(provinceService.getById(project.getProvinceId()));
        }
        return projectsList;
    }

    @Override
    public List<Projects> queryAllByUid(String uid) {
        List<Projects> projectsList = projectsMapper.findProjectsByUid(uid);
        if(!projectsList.isEmpty()){
            projectsList.forEach(item -> item.setProvince(provinceService.getById(item.getProvinceId())));
        }
        return projectsMapper.findProjectsByUid(uid);
    }

    @Override
    public List<Projects> findProjectsByParam(String uid, String projectName) {
        return projectsMapper.findProjectsByParam(uid, projectName);
    }

    /**
     * app根据项目ID修改项目信息
     * @param projectVo 条件参数
     * @return
     */
    @Override
    public boolean updateByIdApp(ProjectVo projectVo) {
        return projectsMapper.updateByIdApp(projectVo);
    }

    @Override
    public void download(List<ProjectsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectsDto Project : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("行政单位", Project.getProvinceId());
            map.put("项目名称", Project.getProjectName());
            map.put("地址", Project.getProjectAddress());
            map.put("负责人", Project.getContactPerson());
            map.put("联系电话", Project.getContactPhone());
            map.put("经度", Project.getXPoint());
            map.put("纬度", Project.getYPoint());
            map.put("备注", Project.getRemark());
            map.put("创建时间", Project.getCreateTime());
            map.put("更新时间", Project.getUpdateTime());
            map.put("是否启用", Project.getEnabled());
            map.put("删除标识", Project.getIsDel());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
