package com.example.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.IAdminService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

/**
 * @author zou17
 */
@Component
@Slf4j
public class TokenUtils {

    private static IAdminService staticAdminService;
    private static final String SECRET_KEY = "zou123456";
//                                               替换为实际的签名密钥

    @Resource
    private IAdminService adminService;

    @PostConstruct
    public void setUserService() {
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
        Date now = new Date();
        Date expireTime = DateUtil.offsetHour(now, 2);
//        2小时后过期
        return Jwts.builder()
                .setSubject(adminId)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, sign.getBytes())
                .compact();
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
            String adminId = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody().getSubject();
            return staticAdminService.getById(Integer.parseInt(adminId));
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败, token={}", token, e);
            return null;
        }
    }
}
