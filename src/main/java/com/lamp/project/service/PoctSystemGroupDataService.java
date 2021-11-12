package com.lamp.project.service;

import com.alibaba.fastjson.JSONObject;
import com.lamp.allUtils.BaseService;
import com.lamp.project.domain.PoctSystemGroupData;
import com.lamp.project.service.dto.PoctSystemGroupDataDto;
import com.lamp.project.service.dto.PoctSystemGroupDataQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author RollingStone-jianyijun
* @date 2020-05-12
*/
public interface PoctSystemGroupDataService extends BaseService<PoctSystemGroupData> {

    /**
     * 获取配置数据
     * @param name 配置名称
     * @return List
     */
    List<JSONObject> getDatas(String name);

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(PoctSystemGroupDataQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxSystemGroupDataDto>
    */
    List<PoctSystemGroupData> queryAll(PoctSystemGroupDataQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<PoctSystemGroupDataDto> all, HttpServletResponse response) throws IOException;
}
