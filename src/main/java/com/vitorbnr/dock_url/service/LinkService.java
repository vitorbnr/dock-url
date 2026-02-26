package com.vitorbnr.dock_url.service;

import com.vitorbnr.dock_url.model.AccessLog;
import com.vitorbnr.dock_url.model.Link;
import com.vitorbnr.dock_url.repository.AccessLogRepository;
import com.vitorbnr.dock_url.repository.LinkRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final AccessLogRepository accessLogRepository;

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private final SecureRandom random = new SecureRandom();

    public LinkService(LinkRepository linkRepository, AccessLogRepository accessLogRepository) {
        this.linkRepository = linkRepository;
        this.accessLogRepository = accessLogRepository;
    }

    @Transactional
    public Link shortenUrl(String originalUrl) {
        String shortCode;

        do {
            shortCode = generateRandomCode();
        } while (linkRepository.existsById(shortCode));

        Link newLink = new Link(shortCode, originalUrl);
        return linkRepository.save(newLink);
    }

    public Optional<Link> getLinkByShortCode(String shortCode) {
        return linkRepository.findById(shortCode);
    }

    @Async
    public void registerAccess(Link link, String ipAddress, String userAgent) {
        AccessLog log = new AccessLog(link, ipAddress, userAgent);
        accessLogRepository.save(log);
        System.out.println("Métrica salva em background para o link: " + link.getShortCode());
    }

    private String generateRandomCode() {
        StringBuilder codeBuilder = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            codeBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}