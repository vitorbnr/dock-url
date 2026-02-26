package com.vitorbnr.dock_url.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class ShortenRequestDTO {

    @NotBlank(message = "A URL não pode estar vazia")
    @URL(message = "A URL informada é inválida")
    private String url;

    public ShortenRequestDTO() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}