package com.lamp.device.controller;

import com.lamp.allUtils.DelelteCommonVo;
import com.lamp.allUtils.ResponseBean;
import com.lamp.device.entity.Device;
import com.lamp.device.service.DeviceService;
import com.lamp.device.service.dto.DeviceQueryCriteria;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author RollingStone-zhourb
 * @date 2021-09-09
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/device")
public class DevicerController {
    private DeviceService deviceService;
    @GetMapping(value="Pageable")
    //@PreAuthorize("@el.check('admin','device:list')")
    public String getDevices(DeviceQueryCriteria criteria, Pageable pageable){
        Map<String, Object> map = deviceService.queryAll(criteria,pageable);
        System.out.println("查询设备结果:"+map);
        return "查询ok";//.ok("查询设备管理成功", deviceService.queryAll(criteria,pageable));
    }
    @GetMapping
    //@PreAuthorize("@el.check('admin','device:list')")
    public String getDevices(DeviceQueryCriteria criteria){
        deviceService.queryAll(criteria);
        List map = deviceService.queryAll(criteria);
        System.out.println("查询设备结果:"+map);
        return "查询ok";//.ok("查询设备管理成功", deviceService.queryAll(criteria,pageable));
    }

    //@Log("新增设备信息")
    @ApiOperation("新增设备信息")
    @PostMapping("/add")
    //@PreAuthorize("@el.check('admin','device:add')")
    public ResponseBean create(@Validated @RequestBody Device resources){
        System.out.println("正在新增设备"+resources);
        if(deviceService.save(resources)){
            System.out.println("新增设备信息成功"+resources);
            return ResponseBean.ok("新增设备信息成功"+resources);
        }else{
            System.out.println("新增设备信息失败"+resources);
            return ResponseBean.error("新增设备信息失败"+resources);
        }
    }
    // @Log("修改设备信息")
    @ApiOperation("修改设备信息")
    @PostMapping("/edit")
    //@PreAuthorize("@el.check('admin','device:edit')")
    public ResponseBean update(@Validated @RequestBody Device resources){
        System.out.println("正在修改设备"+resources);
        if(deviceService.updateById(resources)){
            System.out.println("修改设备信息成功"+resources);
            return ResponseBean.ok("修改设备信息成功"+resources);
        } else {
            System.out.println("修改设备信息失败"+resources);
            return ResponseBean.error("修改设备信息失败"+resources);
        }
    }
    //@Log("删除设备信息")
    @ApiOperation("删除设备信息")
    @PostMapping("/del")
    //@PreAuthorize("@el.check('admin','device:del')")
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
                    deviceService.removeById(id);
                });
            }
            return ResponseBean.ok("删除项目信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("删除失败，请检查是否业务关联。");
        }
    }

}
