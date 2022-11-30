package com.example.springtodo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider tokenProvider;
	private final static String BEARER_PREFIX = "Bearer ";

	private final static String AUTHORIZATION_HEADER = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		try {
			String token = parseBearerToken(request);
			if (token != null) {
				String userId = tokenProvider.verifyToken(token);
				log.info("Authenticated user Id : {}", userId);

				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(
					new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES)
				);
				SecurityContextHolder.setContext(securityContext);
			}
		} catch (Exception e) {
			log.error("JwtAuthentication Error", e);
		}
		filterChain.doFilter(request, response);
	}

	private static String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(BEARER_PREFIX.length());
		}
		return null;
	}
}
