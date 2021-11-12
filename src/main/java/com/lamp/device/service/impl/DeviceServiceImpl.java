package com.lamp.device.service.impl;

import com.github.pagehelper.PageInfo;
import com.lamp.allUtils.IGenerator;
import com.lamp.device.entity.Device;
import com.lamp.device.service.DeviceService;
import com.lamp.device.service.dto.DeviceDto;
import com.lamp.device.service.mapper.DeviceMapper;
import com.lamp.device.service.dto.DeviceQueryCriteria;
import com.lamp.allUtils.QueryHelpPlus;
import com.lamp.allUtils.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author RollingStone-zhourb
 * @date 2021-09-09
 */
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "device")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeviceServiceImpl extends BaseServiceImpl<DeviceMapper, Device> implements DeviceService {

    private final DeviceMapper deviceMapper;
    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(DeviceQueryCriteria criteria, Pageable pageable) {
       getPage(pageable);
        PageInfo<Device> page = new PageInfo<>(queryAll(criteria));
       Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), DeviceDto.class));
        map.put("totalElements", page.getTotal());
        return map;

    }


    @Override
    //@Cacheable
    public List<Device> queryAll(DeviceQueryCriteria criteria) {
        // 项目被软删除了,项目下的网关id作为条件查找deviceList仍然可以查到
        criteria.setDevId("111");
        List<Device> devicesList = baseMapper.selectList(QueryHelpPlus.getPredicate(Device.class, criteria));
        return devicesList;
    }


//    @Override
//    public void download(List<DeviceDto> all, HttpServletResponse response) throws IOException {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (DeviceDto device : all) {
//            Map<String, Object> map = new LinkedHashMap<>();
//            map.put("客户ID", device.getCustomerId());
//            map.put("项目ID", device.getProjectId());
//            map.put("区域ID", device.getAreaId());
//            map.put("网关ID", device.getGatewayId());
//            map.put("产品ID(用来继承物模型设置)", device.getProdEventId());
//            map.put("设备SN", device.getDevSn());
//            map.put("设备别名", device.getDevAlias());
//            map.put("设备安装地址", device.getDevAddr());
//            map.put("设备制造商ID", device.getManu());
//            map.put("设备MAC地址", device.getMac());
//            map.put("协议类型（从字典中获取数据）", device.getProtocolType());
//            map.put("在线状态（从安字典中取数据）", device.getOnlineStatus());
//            map.put("排序号", device.getOrderNum());
//            map.put("设备版本号", device.getDevVersion());
//            map.put("备注", device.getRemark());
//            map.put("最后控制时间", device.getLastControlTime());
//            map.put("创建时间", device.getCreateTime());
//            map.put("更新时间", device.getUpdateTime());
//            map.put("是否启用", device.getEnabled());
//            map.put("删除标识", device.getIsDel());
//            list.add(map);
//        }
//        FileUtil.downloadExcel(list, response);
//    }
//
//    @Override
//    public List<String> findNotEmptyAreaListByIds(String[] ids) {
//        // 获取当前项目的所有区域列表
//        //区域id作为查询条件 如果有设备,就返回false
//        List<String> areaNameList = new ArrayList<>();
//        DeviceQueryCriteria criteria = new DeviceQueryCriteria();
//        //便利ids 根据其中的每一个id获取相应的area对象,areaName,以及判断此area是否包含设备.
//        //如果有设备则返回areaName的list,如果没有则返回null
//        for (String id : ids) {
//            criteria.setAreaId(id);
//       //     ProjectArea area = projectAreaService.getById(id);
//         //   String areaName = area.getAreaName();
//            //根据区域查找设备
//            List<Device> deviceList = baseMapper.selectList(QueryHelpPlus.getPredicate(Device.class, criteria));
//            if (!deviceList.isEmpty()) {
//        //        areaNameList.add(areaName);
//            }
//        }
//        return areaNameList;
//    }
//
//    @Override
//    public List<Device> queryAllByAreaId(String areaId) {
//        //将areaId传入到查询条件中
//        //用baseMapper和查询条件查找devicelist
//        DeviceQueryCriteria criteria = new DeviceQueryCriteria();
//        criteria.setAreaId(areaId);
//        List<Device> deviceList = baseMapper.selectList(QueryHelpPlus.getPredicate(Device.class, criteria));
//        return deviceList;
//    }
//
//    public Map<String, Object> getDeviceAndModelInfo(String mac) throws MyBatisSystemException {
//        Map<String, Object> devAndProductInfo = deviceMapper.getDevAndProductInfo(mac);
//        String devModelId = "";
//        if (devAndProductInfo != null) {
//            devModelId = (String) devAndProductInfo.getOrDefault("dev_model_id", "");
//        }
//        List<Map<String, Object>> modelAndCharacherInfo = deviceMapper.getModelAndCharacherInfo(devModelId);
//        Map<String, Object> result = new HashMap<>();
//        result.put("devAndProductInfo", devAndProductInfo);
//        result.put("modelAndCharacherInfo", modelAndCharacherInfo);
//        return result;
//    }
//
//    public Device getDeviceByMac(String mac) {
//        QueryWrapper<Device> wrapper = new QueryWrapper<>();
//        wrapper.eq("mac", mac);
//        Device data = deviceMapper.selectOne(wrapper);
//        return data;
//    }

}
