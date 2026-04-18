package com.example.weightvolumeservice.security;

import com.example.weightvolumeservice.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthClient {

    private final RestTemplate restTemplate;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    @SuppressWarnings("unchecked")
    public Long validateAndExtractUserId(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or malformed Authorization header.");
        }
        String token = authorizationHeader.substring(7);
        try {
            String url = authServiceUrl + "/api/auth/validate?token=" + token;
            Map<String, Object> response = restTemplate.postForObject(url, null, Map.class);
            if (response == null || !Boolean.TRUE.equals(response.get("valid"))) {
                throw new UnauthorizedException("Invalid or expired token.");
            }
            Number id = (Number) response.get("userId");
            return id.longValue();
        } catch (UnauthorizedException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UnauthorizedException("Token validation failed: " + ex.getMessage());
        }
    }
}
