package com.vitorbnr.dock_url.controller;

import com.vitorbnr.dock_url.model.Link;
import com.vitorbnr.dock_url.dto.ShortenRequestDTO;
import com.vitorbnr.dock_url.dto.ShortenResponseDTO;
import com.vitorbnr.dock_url.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LinkController {

    private final LinkService linkService;

    private static final String DOMAIN = "https://short.local/";

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponseDTO> shortenUrl(@Valid @RequestBody ShortenRequestDTO request) {

        Link savedLink = linkService.shortenUrl(request.getUrl());

        String shortUrl = DOMAIN + savedLink.getShortCode();

        ShortenResponseDTO response = new ShortenResponseDTO(shortUrl, savedLink.getOriginalUrl());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}