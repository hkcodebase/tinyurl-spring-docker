package com.hk.prj.tinyurl_api.api;

import com.hk.prj.tinyurl_api.openapi.api.PingApiDelegate;
import com.hk.prj.tinyurl_api.openapi.model.HealthCheck200Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class PingApiImpl implements PingApiDelegate {

    @Override
    public ResponseEntity<HealthCheck200Response> healthCheck(){
        return ResponseEntity.ok().body(new HealthCheck200Response());
    }
}
