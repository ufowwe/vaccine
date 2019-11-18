package com.vaccine.vaccineapi.service;

import com.vaccine.vaccineapi.config.redis.RedisService;
import com.vaccine.vaccineapi.controller.vo.SessionDTO;
import com.vaccine.vaccineapi.controller.vo.LoginResVO;
import com.vaccine.vaccineapi.controller.vo.WxCode2SessionRes;
import com.vaccine.vaccineapi.entity.User;
import com.vaccine.vaccineapi.exception.BusinessException;
import com.vaccine.vaccineapi.utils.Constants;
import com.vaccine.vaccineapi.utils.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Slf4j
@Service
public class LoginService {

    private static final String appid = "wx561935ed00ba2f46";
//    private static final String appid = "wx609aa3c6a9be088a";
    private static final String secret = "51237145f7db3be1378a2ab42d13f487";
//    private static final String secret = "";
    private static final String grant_type = "authorization_code";

    private static final String code2Session_url = "https://api.weixin.qq.com/sns/jscode2session?";


    @Resource
    private RestTemplateUtil restTemplateUtil;

    @Resource
    private CodeService codeService;

    @Resource
    private RedisService redisService;

    @Resource
    private IUserService userService;

    /**
     * 登陆
     * @param code
     */
    public LoginResVO login(String code) {
        String url = code2Session_url + "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
        ResponseEntity<WxCode2SessionRes> res = restTemplateUtil.get(url, WxCode2SessionRes.class);
        WxCode2SessionRes body = res.getBody();
        log.info("body={}", body);
        if (StringUtils.isBlank(body.getOpenid()) || StringUtils.isBlank(body.getSession_key())) {
            //登陆失败
            throw new BusinessException("登陆失败");
        }
        //登陆成功，生成token
        User u = userService.getByOpenid(body.getOpenid());
        if (u == null) {
            u = new User();
            u.setUserCode(codeService.UUID());
            u.setOpenid(body.getOpenid());
            u.setCreateDate(LocalDateTime.now());
            userService.save(u);
        }
        SessionDTO sessionDTO = new SessionDTO(body.getOpenid(), body.getSession_key(), u.getUserCode(), u.getId());
        String uuid = codeService.UUID();
        //保存到缓存
        redisService.set(uuid, sessionDTO, Constants.LOGIN_TIMEOUT);
        LoginResVO loginResVO = new LoginResVO();
        loginResVO.setToken(uuid);
        loginResVO.setNickname(u.getNickname());
        loginResVO.setAvatar(u.getAvatar());
        return loginResVO;
    }

}
