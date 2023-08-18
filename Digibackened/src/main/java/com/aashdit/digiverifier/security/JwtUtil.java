package com.aashdit.digiverifier.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.admin.service.UserService;
import com.aashdit.digiverifier.login.service.UserDetailsServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUtil {

    @Autowired
    private UserDetailsServiceImpl service;
    
    @Autowired
    private UserService userService;

    private String secret = "a@shd1t_k1pddb65f";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
       try {
    	   return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	} catch (ExpiredJwtException e) {
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getDecoder();
		String payload = new String(decoder.decode(chunks[1]));
		JSONObject object = new JSONObject(payload);
		String userName = object.getString("sub");
		User user = userService.findByUsername(userName).getData();
		user.setIsLoggedIn(false);
		userService.saveUserLoginData(user);
		log.debug("JWT Expired-->",e);
	} catch (UnsupportedJwtException e) {
		log.debug("UnsupportedJwtException-->",e);
	} catch (MalformedJwtException e) {
		log.debug("MalformedJwtException-->",e);
	} catch (SignatureException e) {
		log.debug("SignatureException-->",e);
	} catch (IllegalArgumentException e) {
		log.debug("IllegalArgumentException-->",e);
	}
	return null;
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        UserDetails userDetails = service.loadUserByUsername(username);
        return Jwts.builder().setClaims(claims).setSubject(username)
                .claim("authorities", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 28800000)) //2hr 7200000 //15s 15000 //8hr 28800000
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUserName()) && !isTokenExpired(token));
    }

}
