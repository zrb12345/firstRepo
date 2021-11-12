package com.lamp.project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.lamp.allUtils.CommonEnum;
import com.lamp.allUtils.FileUtil;
import com.lamp.allUtils.IGenerator;
import com.lamp.allUtils.QueryHelpPlus;
import com.lamp.allUtils.impl.BaseServiceImpl;
import com.lamp.project.domain.PoctSystemGroupData;
import com.lamp.project.service.PoctSystemGroupDataService;
import com.lamp.project.service.dto.PoctSystemGroupDataDto;
import com.lamp.project.service.dto.PoctSystemGroupDataQueryCriteria;
import com.lamp.project.service.mapper.SystemGroupDataMapper;

import lombok.AllArgsConstructor;
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
import java.util.stream.Collectors;

/**
* @author RollingStone-jianyijun
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PoctSystemGroupDataServiceImpl extends BaseServiceImpl<SystemGroupDataMapper, PoctSystemGroupData> implements PoctSystemGroupDataService {

    private final IGenerator generator;

    // @Autowired
    // private QiNiuService qiNiuService;

    /**
     * 获取配置数据
     * @param name 配置名称
     * @return List
     */
    @Override
    //@Cacheable(value = "rollingstone:configDatas",key = "#name")
    public List<JSONObject> getDatas(String name) {
        List<PoctSystemGroupData> systemGroupDatas = this.baseMapper
                .selectList(Wrappers.<PoctSystemGroupData>lambdaQuery()
                        .eq(PoctSystemGroupData::getGroupName,name)
                        .eq(PoctSystemGroupData::getStatus, CommonEnum.SHOW_STATUS_1.getValue())
                        .orderByDesc(PoctSystemGroupData::getSort));

        List<JSONObject> list = systemGroupDatas
                .stream()
                .map(PoctSystemGroupData::getValue)
                .map(JSONObject::parseObject)
                .collect(Collectors.toList());

        return list;
    }


    //===============管理后台==============

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(PoctSystemGroupDataQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<PoctSystemGroupData> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        List<PoctSystemGroupDataDto> systemGroupDataDTOS = new ArrayList<>();

        // QiniuConfig qiNiuConfig = qiNiuService.find();
        for (PoctSystemGroupData systemGroupData : page.getList()) {
            PoctSystemGroupDataDto systemGroupDataDTO = generator.convert(systemGroupData, PoctSystemGroupDataDto.class);
            JSONObject valueObject = JSONObject.parseObject(systemGroupData.getValue());

            systemGroupDataDTO.setMap(valueObject);
            systemGroupDataDTOS.add(systemGroupDataDTO);
        }
        map.put("content",systemGroupDataDTOS);
        map.put("totalElements",page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<PoctSystemGroupData> queryAll(PoctSystemGroupDataQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(PoctSystemGroupData.class, criteria));
    }


    @Override
    public void download(List<PoctSystemGroupDataDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PoctSystemGroupDataDto yxSystemGroupData : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("对应的数据名称", yxSystemGroupData.getGroupName());
            map.put("数据组对应的数据值（json数据）", yxSystemGroupData.getValue());
            map.put("添加数据时间", yxSystemGroupData.getAddTime());
            map.put("数据排序", yxSystemGroupData.getSort());
            map.put("状态（1：开启；2：关闭；）", yxSystemGroupData.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
