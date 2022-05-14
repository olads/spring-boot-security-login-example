package com.migia.simpleLogin.securityConfig.jwt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

public class JwtUtils {
	
	private static final String jwtSecretKey = "secretKey";
	

	public static String parseJwt(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, "auth");
		var jwt = cookie.getValue();
		System.out.println(jwt);
		
		return jwt;
	}
	
	public static String generateJwt(String name) {
		return null;
	}
	
	public static boolean validateJwt(String jwt) {
		return false;
	}
	
	public static Cookie createCookie(String jwt) {
		return null;
	}
	
}
