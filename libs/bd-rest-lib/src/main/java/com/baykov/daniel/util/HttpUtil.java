package com.baykov.daniel.util;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class HttpUtil {

    public static String getRequestBody(ServletRequest request) throws IOException {
        return request.getReader()
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static String getRequestHeaders(HttpServletRequest request) {
        Map<String, String> headersMap = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }

        return headersMap.toString();
    }

    public static String getRequestUrlAsString(HttpServletRequest request) {
        StringBuilder urlBuilder = new StringBuilder();

        String protocol = request.getProtocol().toLowerCase();
        String serverName = request.getServerName();
        int port = request.getServerPort();

        urlBuilder.append(protocol).append("://").append(serverName);
        if (port != 80 && port != 443) {
            urlBuilder.append(":").append(port);
        }

        String requestURI = request.getRequestURI();
        urlBuilder.append(requestURI);

        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isEmpty()) {
            urlBuilder.append("?").append(queryString);
        }

        return urlBuilder.toString();
    }
}
