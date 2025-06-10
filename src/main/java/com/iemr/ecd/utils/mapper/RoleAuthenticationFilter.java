package com.iemr.ecd.utils.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.iemr.ecd.service.masters.MasterServiceImpl;
import com.iemr.ecd.utils.constants.Constants;
import com.iemr.ecd.utils.redis.RedisStorage;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class RoleAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisStorage redisService;

    @Autowired
    private MasterServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException, java.io.IOException {
    	String jwtFromCookie = getJwtTokenFromCookies(request);
		String jwtFromHeader = request.getHeader(Constants.JWT_TOKEN);

		String jwtToken = jwtFromCookie != null ? jwtFromCookie : jwtFromHeader;
		Claims extractAllClaims = jwtUtil.extractAllClaims(jwtToken);
		
            String userId = (String) extractAllClaims.get("userId");

            String authRole = redisService.getUserRoleFromCache(Long.valueOf(userId));
            if (authRole == null) {
                String role = userService.getUserRole(Long.valueOf(userId));
                authRole = "ROLE_"+role.toUpperCase();
                redisService.cacheUserRole(Long.valueOf(userId), authRole);
            }

            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(authRole));

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        

        filterChain.doFilter(request, response);
}
    private String getJwtTokenFromCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(Constants.JWT_TOKEN)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}