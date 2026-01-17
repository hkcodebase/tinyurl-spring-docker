package com.hk.prj.tinyurl_api.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table
public class Url {

    @PrimaryKey
    @Column("short_code")
    private String shortCode;
    @Column("original_url")
    private String originalUrl;
    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Url(String originalUrl, String shortCode, LocalDateTime createdTimestamp) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.createdTimestamp = createdTimestamp;
    }

    public Url(){

    }
}
