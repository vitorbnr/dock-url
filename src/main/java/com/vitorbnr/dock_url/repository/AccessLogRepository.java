package com.vitorbnr.dock_url.repository;

import com.vitorbnr.dock_url.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    long countByLink_ShortCode(String shortCode);
}