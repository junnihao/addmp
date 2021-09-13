package com.addmp.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 身份认证过滤器
 */
@Slf4j
@Component
public class JwtAuthentication1Filter extends OncePerRequestFilter {

    @Value("${allowedOrigins}")
    Set<String> allowedOrigins;

    /*@Resource
    private JwtUtil jwtUtil;*/

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {
        log.info("This is the commom security for all app....... ") ;
        // 解决跨域问题
        String originHeader= request.getHeader("Origin");
        if (allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
        }
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With, token, x-token");

        // 明确允许通过的方法，不建议使用*
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", "*");

        // 预请求后，直接返回
        // 返回码必须为 200 否则视为请求失败
        /*if ("OPTIONS".equals(request.getMethod())) {
            return;
        }*/

        /*final String token = this.jwtUtil.getTokenFromRequest(request);
        if (StringUtils.isEmpty(token) || "null".equals(token)) {
            log.info("=> Anonymous<{}> request URL<{}> Method<{}>", IpUtil.getIpAddress(request), request.getRequestURL(), request.getMethod());
        } else {
            try {
                final String username = this.jwtUtil.getUsername(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    final UsernamePasswordAuthenticationToken authentication = this.jwtUtil.getAuthentication(username, token);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (ExpiredJwtException e){
                // 过期
                response.setStatus(403);
                return;

            }
        }*/

        filterChain.doFilter(request, response);
    }
}


