package com.pzand.apicommon.service;

import com.pzand.apicommon.model.entity.InterfaceInfo;

/**
 * @author pzand
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2024-07-17 21:55:18
 */
public interface InnerInterfaceInfoService {
    /**
     * 从数据库中查询模拟接口是否存在 (请求路径，请求方法，请求参数)
     *
     * @param url
     * @param method
     */
    InterfaceInfo getInterfaceInfo(String url, String method);
}
