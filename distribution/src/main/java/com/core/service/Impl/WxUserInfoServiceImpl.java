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

    @Override
    public int countThirdFans(WxUserInfo obj) {
        if (obj!=null&&obj.getUserId() != null
                && StringUtils.isNotEmpty(obj.getUserId().toString())) {
            List<Integer> parents=userDao.selectGrandSons(obj.getUserId());
            if(parents!=null&&parents.size()>0){
                WxUserInfoExample example = new  WxUserInfoExample();
                example.createCriteria().andParentidIn(parents).andSubscribeEqualTo(1);
                return userDao.countByExample(example);
            }
        }
        return 0;
    }

    @Override
    public WxUserInfo getUserIdByOpenIdSub(String openId) {
        WxUserInfoExample example = new WxUserInfoExample();
        WxUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<WxUserInfo> list = userDao.selectByExample(example );
        if (list!=null && list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<WxUserInfo> getFirstList(Integer userId,Integer pageNo) {
        WxUserInfoExample example = new  WxUserInfoExample();
        example.createCriteria().andSubscribeEqualTo(1).andParentidEqualTo(userId);
        if (pageNo!=null){
            example.setLimitClauseCount(10);
            example.setLimitClauseStart(pageNo);
        }
        return userDao.selectByExample(example);
    }

    @Override
    public List<WxUserInfo> getSecondList(Integer userId, Integer pageNo) {
        List<Integer> parents=userDao.selectSons(userId);
        WxUserInfoExample example = new  WxUserInfoExample();
        if (parents!=null&&parents.size()>0){
            example.createCriteria().andParentidIn(parents).andSubscribeEqualTo(1);
            if (pageNo!=null){
                example.setLimitClauseCount(10);
                example.setLimitClauseStart(pageNo);
            }
            return userDao.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<WxUserInfo> getThirdList(Integer userId, Integer pageNo) {
        List<Integer> parents=userDao.selectGrandSons(userId);
        WxUserInfoExample example = new  WxUserInfoExample();
        if (parents!=null&&parents.size()>0){
            example.createCriteria().andParentidIn(parents).andSubscribeEqualTo(1);
            if (pageNo!=null){
                example.setLimitClauseCount(10);
                example.setLimitClauseStart(pageNo);
            }
            return userDao.selectByExample(example);
        }
        return null;
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
