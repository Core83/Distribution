package com.core.service.Impl;

import com.core.dao.WxUserInfoMapper;
import com.core.model.WxUserInfo;
import com.core.model.WxUserInfoExample;
import com.core.service.IWxUserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by core on 15/11/4.
 */
@Service
public class WxUserInfoServiceImpl implements IWxUserInfoService{
    @Autowired
    private WxUserInfoMapper userDao;
    @Override
    public Integer insertOrUpdateWxUser(WxUserInfo user) throws Exception {
        WxUserInfoExample example = new WxUserInfoExample();
        WxUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(user.getOpenId());
        List<WxUserInfo> list = userDao.selectByExample(example );
        if (list!=null && list.size()>0) {
            return userDao.updateByExampleSelective(user,example);
        }
        return userDao.insertSelective(user);
    }

    @Override
    public WxUserInfo getUserIdByOpenId(String openId) {
        WxUserInfoExample example = new WxUserInfoExample();
        WxUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId).andSubscribeEqualTo(1);
        List<WxUserInfo> list = userDao.selectByExample(example );
        if (list!=null && list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public WxUserInfo getUserByTicket(String ticket) {
        WxUserInfoExample example = new WxUserInfoExample();
        WxUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andTicketEqualTo(ticket).andSubscribeEqualTo(1);
        List<WxUserInfo> list = userDao.selectByExample(example );
        if (list!=null && list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public WxUserInfo getUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public WxUserInfo getParentOpenId(Integer userId) {
        WxUserInfo parent=userDao.selectByPrimaryKey(userDao.selectByPrimaryKey(userId).getParentid());
        return parent;
    }

    @Override
    public int countFamily(WxUserInfo obj) {
        WxUserInfoExample example =getCondition(obj);
        return userDao.countByExample(example);
    }

    @Override
    public int countSenFans(WxUserInfo obj) {
//        WxUserInfoExample nexample =getCondition(obj);
        if (obj!=null&&obj.getUserId() != null
                && StringUtils.isNotEmpty(obj.getUserId().toString())) {
            List<Integer> parents=userDao.selectSons(obj.getUserId());
            if(parents!=null&&parents.size()>0){
                WxUserInfoExample example = new  WxUserInfoExample();
                example.createCriteria().andParentidIn(parents);
                return userDao.countByExample(example);
            }
        }
        return 0;

    }

    private  WxUserInfoExample getCondition(WxUserInfo obj) {
        WxUserInfoExample example = new  WxUserInfoExample();
        WxUserInfoExample.Criteria cr = example.createCriteria();
        if (obj != null) {
            cr.andSubscribeEqualTo(1);
            if (obj.getUserId() != null
                    && StringUtils.isNotEmpty(obj.getUserId().toString())) {
                cr.andParentidEqualTo(obj.getUserId());
            }
        }

        return example;
    }

}
