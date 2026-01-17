package com.hk.prj.tinyurl_api.service;

import com.hk.prj.tinyurl_api.exception.ResourceNotFoundException;
import com.hk.prj.tinyurl_api.model.Url;
import com.hk.prj.tinyurl_api.repository.UrlRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class UrlService {
    private final RandomStringUtils randomUtils = RandomStringUtils.secure();
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public Url getUrl(String shortCode) {
        return this.urlRepository.findById(shortCode).orElse(new Url());
    }

    public String generateShortCode() {
        String code = getCode();
        while(urlRepository.existsById(code)){
            code = getCode();
        }
        return code;
    }

    public String getCode() {
        return randomUtils.nextAlphanumeric(6).toLowerCase();
    }

    public String save(String originalUrl) {
        String code = generateShortCode();
        Url url = new Url(originalUrl, code, LocalDateTime.now());
        urlRepository.save(url);
        return code;
    }

    public String validateAndSave(@NotNull @Valid URI originalUrl) {
        return save(originalUrl.toString());
    }
}
