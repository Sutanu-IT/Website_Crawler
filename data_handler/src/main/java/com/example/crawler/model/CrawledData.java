package com.example.crawler.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity  // Specifies that the class is an entity and is mapped to a database table
@Table(name = "crawled_data")  // Specifies the details of the table that this entity will be mapped to
public class CrawledData {

    @Id  // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Specifies the generation strategy for the primary key
    private Long id;  // Primary key of the entity

    @Column(nullable = false, length = 2048)  // Specifies the mapping for a database column
    // length = 2048: Defines the column length for URL which can be quite long
    private String url;  // URL of the crawled data

    @Column(columnDefinition = "TEXT")  // Specifies the mapping for a database column
    // columnDefinition = "TEXT": Uses TEXT data type for potentially long titles
    private String text;  // Text content of the crawled data

    @Column(name = "crawled_at", updatable = false)  // Specifies the mapping for a database column
    // name = "crawled_at": Sets the column name to "crawled_at"
    // updatable = false: Indicates that the column is not updatable
    private LocalDateTime crawledAt;  // Timestamp for when the data was crawled

    // Constructors
    public CrawledData() {
    }

    // Constructor with parameters for initializing the object
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
