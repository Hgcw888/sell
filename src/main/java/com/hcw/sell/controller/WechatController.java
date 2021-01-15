package com.hcw.sell.controller;

import com.hcw.sell.enums.ResultEnum;
import com.hcw.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * @author hgcw
 * @date 2021/1/11 21:01
 */
//微信公众号支付SDK接口
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService wxMpService;

    //传入微信公众号的SDKreturnUrl
    @GetMapping("/wechat")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //1.配置
        //2.调用方法
        //首先构造网页授权url，然后构成超链接让用户点击：
        String url = "http://wei-sell.natapp1.cc/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        //当用户同意授权后，会回调所设置的url并把authorization code传过来，然后用这个code获得access token，其中也包含用户的openid等信息
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            //用户授权完成或重定向链接，无需urlencode。
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openId" + openId;

    }

}
