package com.hk.prj.tinyurl_api.api;

import com.hk.prj.tinyurl_api.ShortCodeService;
import com.hk.prj.tinyurl_api.openapi.api.RedirectApiDelegate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class RedirectApiImpl implements RedirectApiDelegate {

    private final ShortCodeService shortCodeService;

    public RedirectApiImpl(ShortCodeService shortCodeService) {
        this.shortCodeService = shortCodeService;
    }

    @Override
    public ResponseEntity<Void> redirectToOriginalUrl(String shortCode) {
        String originalUrl = shortCodeService.getUrl(shortCode);
        if(StringUtils.isEmpty(originalUrl))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        MultiValueMap<String, String> headers =  new LinkedMultiValueMap<>();
        headers.add("Location", shortCodeService.getUrl(shortCode));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
