package com.baykov.daniel.filter;


import com.baykov.daniel.config.SwaggerConstantConfig;
import com.baykov.daniel.entity.LogDataEntity;
import com.baykov.daniel.filter.util.CachedHttpServletRequest;
import com.baykov.daniel.repository.LogDataRepository;
import com.baykov.daniel.util.HttpUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditLogFilter extends OncePerRequestFilter {

    private final Environment environment;
    private final LogDataRepository logDataRepository;
    private final SwaggerConstantConfig swaggerConstantConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (swaggerConstantConfig.isSwaggerEndpoint(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        long startTime = System.currentTimeMillis();

        CachedHttpServletRequest cachedRequest = new CachedHttpServletRequest(request);

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        try {
            filterChain.doFilter(cachedRequest, response);
            log(cachedRequest, response, timeTaken);
        } catch (Exception e) {
            log.error("Failed to log data: {}, {}", e.getMessage(), e);
        }
    }

    private void log(CachedHttpServletRequest cachedRequest, HttpServletResponse response, long timeTaken) throws IOException {
        LogDataEntity.LogDataEntityBuilder logData = LogDataEntity.builder();
        logData.serviceName(environment.getProperty("spring.application.name"));
        logData.httpMethod(cachedRequest.getMethod());
        logData.endpoint(HttpUtil.getRequestUrlAsString(cachedRequest));
        logData.requestBody(HttpUtil.getRequestBody(cachedRequest));
        logData.requestHeaders(HttpUtil.getRequestHeaders(cachedRequest));
        logData.responseHttpStatusCode(response.getStatus());
        logData.ipAddress(cachedRequest.getRemoteAddr());
        logData.userAgent(cachedRequest.getHeader(HttpHeaders.USER_AGENT));
        logData.timeTakenMs(timeTaken);

        logDataRepository.save(logData.build());
    }
}
