package com.example.inuphonebook.common.Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UrlEncoder {

    private String encodingUrl;

    public UrlEncoder(String baseUrl) throws UnsupportedEncodingException {
        String encodeResult = URLEncoder.encode(baseUrl, StandardCharsets.UTF_8);
        encodingUrl = Base64.getUrlEncoder().encodeToString(encodeResult.getBytes());
    }

    public String getEncodingUrl() {
        return encodingUrl;
    }
}
