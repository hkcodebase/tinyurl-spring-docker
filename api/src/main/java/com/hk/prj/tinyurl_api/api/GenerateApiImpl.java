package com.hk.prj.tinyurl_api.api;


import com.hk.prj.tinyurl_api.openapi.api.GenerateApiDelegate;
import com.hk.prj.tinyurl_api.openapi.model.GenerateShortUrl200Response;
import com.hk.prj.tinyurl_api.openapi.model.GenerateShortUrlRequest;
import com.hk.prj.tinyurl_api.service.ShortCodeService;
import com.hk.prj.tinyurl_api.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class GenerateApiImpl implements GenerateApiDelegate {

    private final UrlService urlService;
    public ShortCodeService shortCodeService;

    public GenerateApiImpl(ShortCodeService shortCodeService, UrlService urlService){
        this.shortCodeService = shortCodeService;
        this.urlService = urlService;
    }

    @Override
    public ResponseEntity<GenerateShortUrl200Response> generateShortUrl(GenerateShortUrlRequest generateShortUrlRequest) {
        GenerateShortUrl200Response generateShortUrl200Response = new GenerateShortUrl200Response();
        String code = urlService.validateAndSave(generateShortUrlRequest.getOriginalUrl());
        URI uri =  ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/redirect/{code}")
                .encode().buildAndExpand(code).toUri();
        generateShortUrl200Response.setShortenedUrl(uri);
        return ResponseEntity.ok(generateShortUrl200Response);
    }

}
