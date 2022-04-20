package com.example.demo.system.service.iface;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface IRbacService {
    /**
     * 是否有权限访问
     *
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
