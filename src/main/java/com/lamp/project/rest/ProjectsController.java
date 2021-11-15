package com.lamp.project.rest;

import com.lamp.allUtils.DelelteCommonVo;
import com.lamp.allUtils.ResponseBean;
import com.lamp.project.domain.ProjectVo;
import com.lamp.project.domain.Projects;
import com.lamp.project.service.ProjectsService;
import com.lamp.project.service.dto.ProjectsQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author RollingStone-zhourb
 * @date 2020-11-13
 */
@AllArgsConstructor
@Api(tags = "项目管理")
@RestController
@RequestMapping("/api/iot/projects")
public class ProjectsController {

    private final ProjectsService projectsService;
   // private final UserProjectsService userProjectsService;

//    //@Log("导出数据")
//    @ApiOperation("导出数据")
//    @GetMapping(value = "/download")
//    //@PreAuthorize("@el.check('admin','Projects:list')")
//    public void download(HttpServletResponse response, ProjectsQueryCriteria criteria) throws IOException {
//        projectsService.download(generator.convert(projectsService.queryAll(criteria), ProjectsDto.class), response);
//    }

    //@Log("查询项目信息")
    @ApiOperation("查询项目信息")
    @GetMapping
    //@PreAuthorize("@el.check('admin','Projects:list')")
    public Map<String, Object> getProjects(ProjectsQueryCriteria criteria, Pageable pageable) {
        projectsService.queryAll(criteria, pageable);
        return  projectsService.queryAll(criteria, pageable);
    }

//    //@Log("新增项目信息")
//    @ApiOperation("新增项目信息")
//    @PostMapping("/add")
//   // @PreAuthorize("@el.check('admin','Projects:add')")
//    public ResponseBean create(@Validated @RequestBody Projects resources) {
//        if (projectsService.save(resources)) {
//            this.addUserProjects(resources);
//            return ResponseBean.ok("创建项目成功");
//        } else {
//            return ResponseBean.error("创建项目失败");
//        }
//    }

    //@Log("修改项目信息")
    @ApiOperation("修改项目信息")
    @PostMapping("/edit")
    //@PreAuthorize("@el.check('admin','Projects:edit')")
    public ResponseBean update(@Validated @RequestBody Projects resources) {
        if (projectsService.updateById(resources)) {
            return ResponseBean.ok("修改项目信息成功");
        } else {
            return ResponseBean.error("修改项目信息失败");
        }
    }

    //@Log("app根据项目ID修改项目信息")
    @ApiOperation("app根据项目ID修改项目信息")
    @PostMapping("/edit/app")
    //@PreAuthorize("@el.check('admin','Projects:edit')")
    public ResponseBean updateApp(@RequestBody @Validated ProjectVo resources) {
        if (projectsService.updateByIdApp(resources)) {
            return ResponseBean.ok("修改项目信息成功");
        } else {
            return ResponseBean.error("修改项目信息失败");
        }
    }


   // @Log("删除项目信息")
    @ApiOperation("删除项目信息")
    @PostMapping("/del")
   // @PreAuthorize("@el.check('admin','Projects:del')")
    public ResponseBean deleteAll(@RequestBody DelelteCommonVo delelteCommonVo) {
        try {
            String[] ids;
            if (delelteCommonVo.getIdStrings() != null && !delelteCommonVo.getIdStrings().equals("")) {
                ids = delelteCommonVo.getIdStrings().split(",");
            } else {
                ids = delelteCommonVo.getIds();
            }
            if (ids == null || ids.length == 0) {
                return ResponseBean.error("删除失败，ID不能为空");
            } else {
                Arrays.asList(ids).forEach(id -> {
                    projectsService.removeById(id);
                });
            }
            return ResponseBean.ok("删除项目信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("删除失败，请检查是否业务关联。");
        }
    }


//    //@Log("根据当前用户查询关联项目")
//    @ApiOperation("根据当前用户查询关联项目")
//    @GetMapping("/user/list")
//   // @PreAuthorize("@el.check('admin','Projects:list')")
//    public ResponseBean findProjectsByUid(ProjectsQueryCriteria criteria) {
//       // String uid = SecurityUtils.getUserId();
//        if (criteria.getProjectName() == null) {
//            return ResponseBean.ok("获取当前用户的项目成功", projectsService.queryAllByUid(uid));
//        } else {
//            return ResponseBean.ok("获取当前用户的项目成功", projectsService.findProjectsByParam(uid, criteria.getProjectName()));
//        }
//    }

    //@Log("查询所有项目")
    @ApiOperation("查询所有项目")
    @GetMapping("/all/list")
    //@PreAuthorize("@el.check('admin','Projects:list')")
    public ResponseBean findAllProjects() {
        ProjectsQueryCriteria criteria = new ProjectsQueryCriteria();
        return ResponseBean.ok("获取所有项目信息成功", projectsService.queryAll(criteria));
    }

//    // 添加用户项目关联
//    private void addUserProjects(Projects project) {
//        // 向当前用户写入项目关联
//        String currentUserId = SecurityUtils.getUserId();
//        UserProjects userProject = new UserProjects();
//        userProject.setUserId(currentUserId);
//        userProject.setProjectId(project.getProjectId());
//
//        UserProjects userProjects = userProjectsService.getOne(new QueryWrapper<UserProjects>().lambda()
//                .eq(UserProjects::getUserId, currentUserId)
//                .eq(UserProjects::getProjectId, project.getProjectId()));
//
//        if (userProjects == null) {
//            userProjectsService.save(userProject);
//        }
//    }
}
