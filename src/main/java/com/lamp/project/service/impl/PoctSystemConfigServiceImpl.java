package com.lamp.project.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lamp.allUtils.FileUtil;
import com.lamp.allUtils.IGenerator;
import com.lamp.allUtils.QueryHelpPlus;
import com.lamp.allUtils.RedisUtils;
import com.lamp.allUtils.impl.BaseServiceImpl;
import com.lamp.project.domain.PoctSystemConfig;
import com.lamp.project.service.PoctSystemConfigService;
import com.lamp.project.service.dto.PoctSystemConfigDto;
import com.lamp.project.service.dto.PoctSystemConfigQueryCriteria;
import com.lamp.project.service.mapper.SystemConfigMapper;

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

/**
* @author RollingStone-jianyijun
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PoctSystemConfigServiceImpl extends BaseServiceImpl<SystemConfigMapper, PoctSystemConfig> implements PoctSystemConfigService {

    private final IGenerator generator;
    private final RedisUtils redisUtils;
//    @Autowired
//    private QiNiuService qiNiuService;
    /**
     * 获取配置值
     * @param name 配置名
     * @return string
     */
    @Override
    public String getData(String name) {
        String result = redisUtils.getY(name);
        if(StrUtil.isNotBlank(result)) return result;

        QueryWrapper<PoctSystemConfig> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PoctSystemConfig::getMenuName,name);
        PoctSystemConfig systemConfig = this.baseMapper.selectOne(wrapper);
        if(systemConfig == null) return "";
        return systemConfig.getValue();
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(PoctSystemConfigQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<PoctSystemConfig> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), PoctSystemConfigDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<PoctSystemConfig> queryAll(PoctSystemConfigQueryCriteria criteria){
        List<PoctSystemConfig> systemConfigsList = baseMapper.selectList(QueryHelpPlus.getPredicate(PoctSystemConfig.class, criteria));
        return systemConfigsList;
    }


    @Override
    public void download(List<PoctSystemConfigDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PoctSystemConfigDto yxSystemConfig : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("字段名称", yxSystemConfig.getMenuName());
            map.put("默认值", yxSystemConfig.getValue());
            map.put("排序", yxSystemConfig.getSort());
            map.put("是否隐藏", yxSystemConfig.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public PoctSystemConfig findByKey(String key) {
        return this.getOne(new QueryWrapper<PoctSystemConfig>().lambda()
                .eq(PoctSystemConfig::getMenuName,key));
    }
}
