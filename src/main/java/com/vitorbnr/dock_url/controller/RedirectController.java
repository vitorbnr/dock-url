package com.vitorbnr.dock_url.controller;

import com.vitorbnr.dock_url.model.Link;
import com.vitorbnr.dock_url.service.LinkService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
public class RedirectController {

    private final LinkService linkService;

    public RedirectController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode, HttpServletRequest request) {

        Optional<Link> linkOpt = linkService.getLinkByShortCode(shortCode);

        if (linkOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Link link = linkOpt.get();

        String userAgent = request.getHeader("User-Agent");

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        linkService.registerAccess(link, ipAddress, userAgent);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(link.getOriginalUrl()))
                .build();
    }
}