package com.example.crawler.services;

import com.example.crawler.model.CrawledData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crawler.repository.CrawledDataRepository;
import java.util.List;

@Service  // Indicates that this class provides a business service
public class CrawlerService {
    private final CrawledDataRepository repository;  // Autowired repository for CRUD operations

    @Autowired  // Constructor injection for CrawledDataRepository
    public CrawlerService(CrawledDataRepository repository) {
        this.repository = repository;
    }

    // Method to save crawled data (URL and text content) to the database
    public CrawledData saveCrawledData(String url, String text) {
        CrawledData data = new CrawledData(url, text);  // Creating a new CrawledData object
        return repository.save(data); // Saves the data into the database and returns the saved entity
    }

    // Method to retrieve all crawled data from the database
    public List<CrawledData> findAllCrawledData() {
        return repository.findAll();  // Returns a list of all crawled data
    }
}
