package com.iemr.ecd.utils.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisStorage redisService;

    @Autowired
    private MasterServiceImpl userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, java.io.IOException {
		List<String> authRoles = null;
		try {
			String jwtFromCookie = CookieUtil.getJwtTokenFromCookie(request);
			String jwtFromHeader = request.getHeader(Constants.JWT_TOKEN);

			String jwtToken = jwtFromCookie != null ? jwtFromCookie : jwtFromHeader;
			if(null == jwtToken || jwtToken.trim().isEmpty()) {
				filterChain.doFilter(request, response);
				return;
			}
			Claims extractAllClaims = jwtUtil.extractAllClaims(jwtToken);
			if(null == extractAllClaims) {
				filterChain.doFilter(request, response);
				return;
			}
			Object userIdObj = extractAllClaims.get("userId");
			String userId = userIdObj != null ? userIdObj.toString() : null;

			authRoles = redisService.getUserRoleFromCache(Long.valueOf(userId));
			if (authRoles == null || authRoles.isEmpty()) {
			    List<String> roles = userService.getUserRoles(Long.valueOf(userId)); // assuming this returns multiple roles
			    authRoles = roles.stream()
			    	    .filter(Objects::nonNull)
			    	    .map(String::trim)
			    	    .map(role -> "ROLE_" + role.toUpperCase().replace(" ", "_"))
			    	    .collect(Collectors.toList());
			    redisService.cacheUserRoles(Long.valueOf(userId), authRoles);
			}

			List<GrantedAuthority> authorities = authRoles.stream()
			        .map(SimpleGrantedAuthority::new)
			        .collect(Collectors.toList());

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (Exception e) {
			logger.error("Authentication filter error for request {}: {}", request.getRequestURI(), e.getMessage());
			SecurityContextHolder.clearContext();
		} finally {
			filterChain.doFilter(request, response);
		}

	}
}