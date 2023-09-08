package com.nghia.userservice.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
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

  public String generateToken(String username) {
    Date currentDate = new Date();
    Date expiredDate = new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRATION);
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(currentDate)
        .setExpiration(expiredDate)
        .signWith(key(), SignatureAlgorithm.HS512)
        .compact();
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
