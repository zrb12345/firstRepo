package com.lamp.project.service;

import com.lamp.allUtils.BaseService;
import com.lamp.project.domain.PoctSystemConfig;
import com.lamp.project.service.dto.PoctSystemConfigDto;
import com.lamp.project.service.dto.PoctSystemConfigQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author RollingStone-jianyijun
* @date 2020-05-12
*/
public interface PoctSystemConfigService extends BaseService<PoctSystemConfig> {

    /**
     * 获取配置值
     * @param name 配置名
     * @return string
     */
    String getData(String name);

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(PoctSystemConfigQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxSystemConfigDto>
    */
    List<PoctSystemConfig> queryAll(PoctSystemConfigQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<PoctSystemConfigDto> all, HttpServletResponse response) throws IOException;

    PoctSystemConfig findByKey(String store_brokerage_statu);
}
