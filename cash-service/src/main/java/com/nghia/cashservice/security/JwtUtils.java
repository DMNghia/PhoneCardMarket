package com.nghia.cashservice.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.*;
import io.jsonwebtoken.security.*;
import java.security.Key;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class JwtUtils {

  @Value("${JWT_KEY}")
  private String jwtSecret;
  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getUsernameFromJWT(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key())
        .build()
        .parseClaimsJws(token).getBody().getSubject();
  }
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException expiredJwtException) {
      log.error("JWT EXPIRATION -> {}", expiredJwtException);
    } catch (MalformedJwtException malformedJwtException) {
      log.error("INVALID JWT TOKEN -> {}", malformedJwtException);
    } catch (UnsupportedJwtException unsupportedJwtException) {
      log.error("JWT UNSUPPORTED -> {}", unsupportedJwtException);
    } catch (Exception e) {
      log.error("ERROR -> {}", e);
    }
    return false;
  }

  public String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
