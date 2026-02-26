package com.vitorbnr.dock_url.dto;

public class LinkResponseDTO {

    private String short_url;
    private String original_url;
    private long clicks;

    public LinkResponseDTO(String shortUrl, String originalUrl, long clicks) {
        this.short_url = shortUrl;
        this.original_url = originalUrl;
        this.clicks = clicks;
    }

    public String getShort_url() { return short_url; }
    public void setShort_url(String short_url) { this.short_url = short_url; }

    public String getOriginal_url() { return original_url; }
    public void setOriginal_url(String original_url) { this.original_url = original_url; }

    public long getClicks() { return clicks; }
    public void setClicks(long clicks) { this.clicks = clicks; }
}