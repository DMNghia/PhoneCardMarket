package com.nghia.cashservice.security;

import com.nghia.cashservice.dto.response.ResponseInfo;
import com.nghia.cashservice.service.grpc.userService.UserServiceGrpcIml;
import com.nghia.grpc.entities.user.FindUserByUsernameRequest;
import com.nghia.grpc.entities.user.FindUserResponse;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserServiceGrpcIml userServiceGrpcIml;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    String token = jwtUtils.getJwtFromRequest(request);
    if (StringUtils.hasText(token) && jwtUtils.validateToken(token)) {
      String username = jwtUtils.getUsernameFromJWT(token);

      FindUserResponse userResponse = userServiceGrpcIml.findUserByUsername(
          FindUserByUsernameRequest.newBuilder()
              .setUsername(username)
              .build());
      if (userResponse.getBaseResponse().getCode().equals("00")) {
        GrantedAuthority granted = new SimpleGrantedAuthority(userResponse.getRole().name());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userResponse, null, Collections.singleton(granted));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
    filterChain.doFilter(request, response);
  }

}
