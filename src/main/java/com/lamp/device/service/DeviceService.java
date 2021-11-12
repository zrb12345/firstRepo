package com.lamp.device.service;

import com.lamp.device.entity.Device;
import com.lamp.device.service.dto.DeviceQueryCriteria;
import com.lamp.allUtils.BaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author RollingStone-zhourb
* @date 2021-09-09
*/
public interface DeviceService extends BaseService<Device> {

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String, Object>
    */
    Map<String, Object> queryAll(DeviceQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<DeviceDto>
    */
    List<Device> queryAll(DeviceQueryCriteria criteria);

//    /**
//    * 导出数据
//    * @param all 待导出的数据
//    * @param response /
//    * @throws IOException /
//    */
//    void download(List<DeviceDto> all, HttpServletResponse response) throws IOException;
//    /**
//     * 根据区域ids查设备信息
//     *
//     */
//    List<String> findNotEmptyAreaListByIds(String[] ids);
//
//    /**
//     * 根据区域id查设备信息
//     *
//     */
//    List<Device> queryAllByAreaId(String areaId);
//
//    Map<String, Object> getDeviceAndModelInfo(String mac);
//
//    Device getDeviceByMac(String mac);
}
