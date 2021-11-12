package com.lamp.project.rest;

import com.lamp.allUtils.DelelteCommonVo;
import com.lamp.allUtils.IGenerator;
import com.lamp.allUtils.ResponseBean;
import com.lamp.project.domain.Province;
import com.lamp.project.service.ProvinceService;
import com.lamp.project.service.dto.ProvinceDto;
import com.lamp.project.service.dto.ProvinceQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
* @author RollingStone-jianyijun
* @date 2020-08-18
*/
@AllArgsConstructor
@Api(tags = "行政单位信息管理")
@RestController
@RequestMapping("/api/iot/province")
public class ProvinceController {

    //    @Autowired
    private final ProvinceService provinceService;
    //    @Autowired
    private final IGenerator generator;

    //@Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    //@PreAuthorize("@el.check('admin','iotProvince:list')")
    public void download(HttpServletResponse response, ProvinceQueryCriteria criteria) throws IOException {
        provinceService.download(generator.convert(provinceService.queryAll(criteria), ProvinceDto.class), response);
    }

    //@Log("查询行政单位信息")
    @ApiOperation("查询行政单位信息")
    @GetMapping
   // @PreAuthorize("@el.check('admin','iotProvince:list')")
    public ResponseBean getIotProvinces(ProvinceQueryCriteria criteria, Pageable pageable) {
        List<ProvinceDto> provinceDtos = generator.convert(provinceService.queryAll(criteria), ProvinceDto.class);
        return ResponseBean.ok("获取行政单位成功", provinceService.buildTree(provinceDtos));
    }

    //@Log("新增行政单位信息")
    @ApiOperation("新增行政单位信息")
    @PostMapping(value = "/add")
   // @PreAuthorize("@el.check('admin','iotProvince:add')")
    public ResponseBean create(@Validated @RequestBody Province resources) {
        if (provinceService.create(resources)){
            return ResponseBean.ok("新增行政单位成功");
        } else {
            return ResponseBean.error("新增行政单位失败");
        }
    }

    //@Log("修改行政单位信息")
    @ApiOperation("修改行政单位信息")
    @PostMapping(value = "/edit")
   // @PreAuthorize("@el.check('admin','iotProvince:edit')")
    public ResponseBean update(@Validated @RequestBody Province resources) {
        if(provinceService.updateById(resources)){
            return ResponseBean.ok("修改行政单位信息成功");
        } else {
            return ResponseBean.error("修改行政单位信息失败");
        }
    }

    //@Log("删除行政单位信息")
    @ApiOperation("删除行政单位信息")
    @PostMapping(value = "/del")
    //@PreAuthorize("@el.check('admin','iotProvince:del')")
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
                    provinceService.removeById(id);
                });
            }
            return ResponseBean.ok("删除项目信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("删除失败，请检查是否业务关联。");
        }
    }
}