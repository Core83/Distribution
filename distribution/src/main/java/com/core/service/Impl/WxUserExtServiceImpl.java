package com.core.service.Impl;

import com.core.dao.WxUserExtMapper;
import com.core.model.WxUserExt;
import com.core.model.WxUserExtExample;
import com.core.service.IWxUserExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by core on 15/11/16.
 */
@Service
public class WxUserExtServiceImpl implements IWxUserExtService {
    @Autowired
    private WxUserExtMapper userExtMapper;
    @Override
    public void insert(WxUserExt ext) {
        WxUserExtExample extExample=new WxUserExtExample();
        extExample.createCriteria().andUserIdEqualTo(ext.getUserId()).andExtKeyEqualTo(ext.getExtKey());
        List<WxUserExt> list=userExtMapper.selectByExample(extExample);
        if (list!=null&&list.size()>0){
            if ("LEVEL".equals(ext.getExtKey())){
                if(Integer.valueOf(list.get(0).getExtValue())<Integer.valueOf(ext.getExtValue())){
                    userExtMapper.updateByExampleSelective(ext,extExample);
                }
            }else{
                userExtMapper.updateByExampleSelective(ext,extExample);
            }

        }else{
            userExtMapper.insertSelective(ext);
        }

    }
}
