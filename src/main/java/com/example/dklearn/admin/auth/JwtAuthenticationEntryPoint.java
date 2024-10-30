package com.example.dklearn.admin.auth;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
       // response.setContentType("application/json");
       // response.getOutputStream().print(request.getRequestURL().toString());
        response.setContentType("application/json");
        response.getOutputStream().print("{\"code\":\"cv401\",\"message\":\"Unauthorized.. Please authenticate..\"}");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
