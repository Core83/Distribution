package com.core.util;

import com.iboot.weixin.api.OauthAPI;
import com.iboot.weixin.api.config.ApiConfig;
import com.iboot.weixin.api.enums.OauthScope;
import com.iboot.weixin.api.response.BaseResponse;
import com.iboot.weixin.api.response.OauthGetTokenResponse;
import com.iboot.weixin.util.JSONUtil;

/**
 * Created by core on 15/11/12.
 */
public class NOauthAPI extends OauthAPI{
    public NOauthAPI(ApiConfig config) {
        super(config);
    }
    public String getOpenId(String redirectUrl, OauthScope scope, String state){
        OauthGetTokenResponse response = null;
        String url=this.getOauthPageUrl(redirectUrl,scope,state);
        BaseResponse r = this.executeGet(url);
        String resultJson = this.isSuccess(r.getErrcode())?r.getErrmsg():r.toJsonString();
        response = (OauthGetTokenResponse) JSONUtil.toBean(resultJson, OauthGetTokenResponse.class);
        return response.getOpenid();
    }
}
