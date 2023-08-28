package com.aashdit.digiverifier.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.admin.service.UserService;
import com.aashdit.digiverifier.config.superadmin.service.SuperAdminDashboardServiceImpl;
import com.aashdit.digiverifier.login.model.LoggedInUser;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,OPTIONS");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

		httpServletResponse.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, No-Auth");

		Boolean skipThis = false;
		if (httpServletRequest.getRequestURI().contains("/api/login/authenticate")
				|| httpServletRequest.getRequestURI().contains("/configuration/ui")
				|| httpServletRequest.getRequestURI().contains("/swagger")
				|| httpServletRequest.getRequestURI().contains("/webjars")
				|| httpServletRequest.getRequestURI().contains("/v2")
				|| httpServletRequest.getRequestURI().contains("/api/allowAll")) {
			skipThis = true;
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			if (httpServletRequest.getRequestURI().contains("/api") && skipThis == false) {
				String authorizationHeader = httpServletRequest.getHeader("Authorization");
				String token = null;
				String userName = null;

				if (!httpServletRequest.getMethod().equals("OPTIONS")) {
					if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
						token = authorizationHeader.substring(7);
						if(StringUtils.isNotEmpty(token)){
							userName = jwtUtil.extractUsername(token);
						}
						
						if (userName != null) {
							User user = userService.findByUsername(userName).getData();
							if (user != null) {
								
								try {
									if (jwtUtil.validateToken(token, user)) {

										final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
										grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRoleCode()));

										final LoggedInUser userDetails = new LoggedInUser(user.getUserName(),
												user.getPassword(), true, true, true, true, grantedAuthorities,
												user.getRole(), user);

										final Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
												null, grantedAuthorities);
										final SecurityContext sc = SecurityContextHolder.getContext();
										sc.setAuthentication(auth);
										final HttpSession session = httpServletRequest.getSession(true);
										session.setAttribute("SPRING_SECURITY_CONTEXT", sc);

										filterChain.doFilter(httpServletRequest, httpServletResponse);

									} else {
										user.setIsLoggedIn(false);
										userService.saveUserLoginData(user);
										httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
									}
								} catch (Exception e) {
									log.error("Exception occured in jWT filter-->",e);
									user.setIsLoggedIn(false);
									userService.saveUserLoginData(user);
									httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
								}
							} else {
								httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
							}

						} else {
							httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
						}

					} else {
						httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
					}
				}

			} else {
				filterChain.doFilter(httpServletRequest, httpServletResponse);
			}
		}

	}

}
