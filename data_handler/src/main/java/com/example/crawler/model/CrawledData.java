package com.example.crawler.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "crawled_data")
public class CrawledData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)  // Increased length for URLs which can be quite long
    private String url;

    @Column(columnDefinition = "TEXT")  // Use TEXT for potentially long titles
    private String text;

    @Column(name = "crawled_at", updatable = false)
    private LocalDateTime crawledAt;

    // Constructors
    public CrawledData() {
    }

    public CrawledData(String url, String text) {
        this.url = url;
        this.text = text;
        this.crawledAt = LocalDateTime.now();  // Set the crawl time at creation
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCrawledAt() {
        return crawledAt;
    }

    public void setCrawledAt(LocalDateTime crawledAt) {
        this.crawledAt = crawledAt;
    }
}
