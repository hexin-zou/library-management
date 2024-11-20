package com.example.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;

/**
 * @author zou17
 */
@Component
@Slf4j
public class TokenUtils {

    private static IAdminService staticAdminService;
    //  替换为实际的签名密钥

    @Resource
    private IAdminService adminService;

    @PostConstruct
    public void setUserService()   {
        staticAdminService = adminService;
    }

    /**
     * 生成token
     *
     * @param adminId 管理员ID
     * @param sign    签名密钥
     * @return 生成的token
     */
    public static String genToken(String adminId, String sign) {
//        2小时后过期
    return JWT.create().withAudience(adminId)
            // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
            // 2小时后token过期
                .sign(Algorithm.HMAC256(sign));
    // 以 password 作为 token 的密钥
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return Admin对象
     */
    public static Admin getCurrentAdmin() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败， token: {}", token);
                return null;
            }
            String adminId = JWT.decode(token).getAudience().get(0);
            return staticAdminService.getById(Integer.parseInt(adminId));
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败, token={}", token, e);
            return null;
        }
    }
}
