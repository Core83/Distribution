package com.core.service.Impl;

import com.core.dao.WxMerInfoMapper;
import com.core.model.WxMerInfo;
import com.core.service.IWxMerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by core on 15/11/8.
 */
@Service
public class WxMerInfoServiceImpl implements IWxMerInfoService {
    @Autowired
    private WxMerInfoMapper wxDao;

    @Override
    public List<WxMerInfo> getMerLists() {
        return wxDao.selectByExample(null);
    }

    @Override
    public WxMerInfo getOneById(Integer gdsId) {
        return wxDao.selectByPrimaryKey(gdsId);
    }

    @Override
    public void createMer(WxMerInfo merInfo) {
        wxDao.insertSelective(merInfo);
    }
}
