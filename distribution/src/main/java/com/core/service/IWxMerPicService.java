package com.core.service;

import com.core.model.WxMerPic;
import com.core.model.WxMerPicExample;

import java.util.List;

/**
 * Created by core on 15/11/8.
 */
public interface IWxMerPicService {
    List<WxMerPic> getListByExample(Integer gdsId);
}
