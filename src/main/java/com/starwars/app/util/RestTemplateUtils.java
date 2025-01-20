package com.starwars.app.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for creating {@link HttpEntity} objects used in REST API calls with {@link RestTemplate}.
 * This class provides a method to generate an HTTP entity with default headers.
 */
public class RestTemplateUtils {

    private RestTemplateUtils() {
        //No-Args Constructor
    }

    /**
     * Creates an {@link HttpEntity} with default headers.
     * The default headers include a "User-Agent" header set to mimic a browser request.
     *
     * @return a new {@link HttpEntity} instance with predefined headers.
     */
    public static HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        return new HttpEntity<>(headers);
    }
}
