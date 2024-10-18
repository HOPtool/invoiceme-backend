package com.hoptool.utils;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public  class LoggingFilter implements ClientRequestFilter {
    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        log.info("Sending request to URI: {}", requestContext.getUri());
        log.info("HTTP Method: {}", requestContext.getMethod());
        logHeaders(requestContext.getHeaders());
        logEntity(requestContext.getEntity());
    }

    private void logHeaders(MultivaluedMap<String, Object> headers) {
        for (Map.Entry<String, List<Object>> entry : headers.entrySet()) {
            log.info("Header: {} = {}", entry.getKey(), entry.getValue());
        }
    }

    private void logEntity(Object entity) {
        if (entity != null) {
            log.info("Request body: {}", entity);
        } else {
            log.info("Request body is empty.");
        }
    }
}