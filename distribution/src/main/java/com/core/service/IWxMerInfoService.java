package com.core.service;

import com.core.model.WxMerInfo;

import java.util.List;

/**
 * Created by core on 15/11/8.
 */
public interface IWxMerInfoService {
    List<WxMerInfo> getMerLists();
    WxMerInfo getOneById(Integer gdsId);
    void createMer(WxMerInfo merInfo);
}
