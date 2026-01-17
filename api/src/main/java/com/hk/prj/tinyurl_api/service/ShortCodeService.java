package com.hk.prj.tinyurl_api.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;

@Service
public class ShortCodeService {
    private final RandomStringUtils randomUtils = RandomStringUtils.secure();

    private final HashMap<String, String> shortCodeMap = new HashMap<>();

    public String generateShortCode(@NotNull @Valid URI originalUrl) {
        String code = getCode();
        while(shortCodeMap.containsKey(code)){
            code = getCode();
        }
        shortCodeMap.put(code, originalUrl.toString());
        return code;
    }

    public String getCode() {
        return randomUtils.nextAlphanumeric(6).toLowerCase();
    }

    public String getUrl(String shortCode) {
        return shortCodeMap.get(shortCode);
    }
}
