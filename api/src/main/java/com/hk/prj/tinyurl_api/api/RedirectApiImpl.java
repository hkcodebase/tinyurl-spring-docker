package com.hk.prj.tinyurl_api.api;

import com.hk.prj.tinyurl_api.exception.ResourceNotFoundException;
import com.hk.prj.tinyurl_api.openapi.api.RedirectApiDelegate;
import com.hk.prj.tinyurl_api.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RedirectApiImpl implements RedirectApiDelegate {

    private final UrlService urlService;

    public RedirectApiImpl(UrlService urlService) {
        this.urlService = urlService;
    }

    @Override
    public ResponseEntity<Void> redirectToOriginalUrl(String shortCode) {
        String originalUrl = urlService.getUrl(shortCode).getOriginalUrl();
        if(StringUtils.isEmpty(originalUrl))
            throw new ResourceNotFoundException("this url not found");

        HttpHeaders headers =  new HttpHeaders();
        headers.add("Location", originalUrl);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
