package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzand.apicommon.model.entity.UserInterfaceInfo;

public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    boolean invokeCount(long interfaceInfoId, long userId);

    boolean hasInvokeCount(long interfaceInfoId, long userId);
}
