package com.lamp.project.service;


import com.lamp.allUtils.BaseService;
import com.lamp.project.domain.Province;
import com.lamp.project.service.dto.ProvinceDto;
import com.lamp.project.service.dto.ProvinceQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author RollingStone-jianyijun
* @date 2020-08-18
*/
public interface ProvinceService extends BaseService<Province> {

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String, Object>
    */
    Map<String, Object> queryAll(ProvinceQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<IotProvinceDto>
    */
    List<Province> queryAll(ProvinceQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ProvinceDto> all, HttpServletResponse response) throws IOException;

    /**
     * 构建树形数据
     * @param provinceDtos 原始数据
     * @return 树形数据
     */
    Object buildTree(List<ProvinceDto> provinceDtos);

    /**
     * 添加行政单位信息
     * @param province
     * @return
     */
    boolean create(Province province);
}
