package com.vitorbnr.dock_url.repository;

import com.vitorbnr.dock_url.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, String> {
}