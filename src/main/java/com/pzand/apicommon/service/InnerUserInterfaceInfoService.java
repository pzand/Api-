package com.pzand.apicommon.service;

/**
 * @author pzand
 * @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service
 * @createDate 2024-07-21 17:30:53
 */
public interface InnerUserInterfaceInfoService {

    boolean invokeCount(long interfaceInfoId, long userId);

    boolean hasInvokeCount(long interfaceInfoId, long userId);
}
