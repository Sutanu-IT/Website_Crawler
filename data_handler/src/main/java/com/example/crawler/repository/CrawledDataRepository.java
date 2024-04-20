package com.example.crawler.repository;

import com.example.crawler.model.CrawledData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Annotation to indicate that this interface is a repository
@Repository
// JpaRepository<CrawledData, Long> - JpaRepository for CrawledData entity with primary key of type Long
public interface CrawledDataRepository extends JpaRepository<CrawledData, Long> {
}
