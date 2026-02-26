package com.vitorbnr.dock_url.dto;

public class ShortenResponseDTO {

    private String short_url;
    private String original_url;

    public ShortenResponseDTO(String shortUrl, String originalUrl) {
        this.short_url = shortUrl;
        this.original_url = originalUrl;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }
}