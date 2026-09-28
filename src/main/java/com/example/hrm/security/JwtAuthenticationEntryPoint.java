package com.example.hrm.security;

import com.example.hrm.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String customErrorMessage = (String) request.getAttribute("jwt_exception_message");
        String finalMessage;

        if ("TOKEN_EXPIRED".equals(customErrorMessage)) {
            finalMessage = "Token đã hết hạn vui lòng đăng nhập lại!";
        } else if (customErrorMessage != null) {
            finalMessage = customErrorMessage;
        } else {
            finalMessage = "Unauthorized: Token không hợp lệ hoặc thiếu Token";
        }

        ApiResponse apiResponse = ApiResponse.builder()
                .code(HttpServletResponse.SC_UNAUTHORIZED)
                .message(finalMessage)
                .status("FAIL")
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}