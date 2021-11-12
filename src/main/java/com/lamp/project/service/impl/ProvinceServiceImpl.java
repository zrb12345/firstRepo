package com.lamp.project.service.impl;

import com.github.pagehelper.PageInfo;
import com.lamp.allUtils.FileUtil;
import com.lamp.allUtils.IGenerator;
import com.lamp.allUtils.QueryHelpPlus;
import com.lamp.allUtils.impl.BaseServiceImpl;
import com.lamp.project.domain.Province;
import com.lamp.project.service.ProvinceService;
import com.lamp.project.service.dto.ProvinceDto;
import com.lamp.project.service.dto.ProvinceQueryCriteria;
import com.lamp.project.service.mapper.ProvinceMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author RollingStone-jianyijun
* @date 2020-08-18
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "iotProvince")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProvinceServiceImpl extends BaseServiceImpl<ProvinceMapper, Province> implements ProvinceService {

    private final IGenerator generator;
    private final ProvinceMapper provinceMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ProvinceQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<Province> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ProvinceDto.class));
        map.put("totalElements", page.getTotal());

        return map;
    }


    @Override
    //@Cacheable
    public List<Province> queryAll(ProvinceQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(Province.class, criteria));
    }


    @Override
    public void download(List<ProvinceDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProvinceDto iotProvince : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("行政单位名称", iotProvince.getProvinceName());
            map.put("父ID（例如：01.01.09）", iotProvince.getParentId());
            map.put("备注", iotProvince.getRemark());
            map.put("创建时间", iotProvince.getCreateTime());
            map.put("更新时间", iotProvince.getUpdateTime());
            map.put("是否启用", iotProvince.getEnabled());
            map.put("删除标识", iotProvince.getIsDel());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Object buildTree(List<ProvinceDto> provinceDtos) {
        if (provinceDtos != null && !provinceDtos.isEmpty()) {
            addRootProvince(provinceDtos);
            ProvinceDto current, next;
            for (int i = 0, len = provinceDtos.size(); i < len; i++) {//如果是i<len-1,会少循环一轮.可能有数据错误
                current = provinceDtos.get(i);
                for (int j = 1; j < len; j++) {//如果不用j<len而是用j<i的格式会出问题
                    next = provinceDtos.get(j);
                    if (next.getParentId().equals(current.getProvinceId())) {
                        current.addChild(next);
                    }
                }
            }
            ProvinceDto provinceDto = provinceDtos.get(0);
            Map<String,Object> map = new HashMap<>(2);
            List<ProvinceDto> dtoList = provinceDto.getChildren();
            map.put("totalElements",dtoList.size());
            map.put("content", dtoList);
            return map;
        } else {
            Map<String,Object> map = new HashMap<>(2);
            map.put("totalElements", 0);
            map.put("content", Collections.EMPTY_LIST);
            return map;
        }
    }

    @Override
    public boolean create(Province province) {
        if (province.getParentId() == null) {
            province.setParentId("000");
        }
        int countNum = provinceMapper.countProvinceNumberByPid(province.getParentId());
        int num = countNum + 1;
        String id = province.getParentId() +","+ String.format("%03d", num);
        province.setProvinceId(id);

        String[]  idlist =  id.split(",");
        String ProvinceAllName = "";
        String realId = "";
        for (String idStr : idlist){
            if(idStr.equals("000")){
                realId =idStr;
            } else {
                realId += ","+idStr;
                Province cProvice = provinceMapper.selectById(realId);
                if(cProvice != null){
                    ProvinceAllName += "-" + cProvice.getProvinceName();
                }
            }
        }
        ProvinceAllName += "-" + province.getProvinceName();
        province.setFullName(ProvinceAllName.substring(1,ProvinceAllName.length()));
        baseMapper.insert(province);
        return true;
    }

    private void addRootProvince(List<ProvinceDto> provinceDtos) {
        ProvinceDto provinceDto = new ProvinceDto();
        provinceDto.setProvinceId("000");
        provinceDtos.add(0, provinceDto);
    }
}
