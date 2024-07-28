package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzand.apicommon.model.entity.InterfaceInfo;

/**
* @author pzand
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-07-17 21:55:18
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
