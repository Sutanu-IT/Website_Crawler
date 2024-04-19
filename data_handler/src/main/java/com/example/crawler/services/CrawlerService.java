package com.example.crawler.services;

import com.example.crawler.model.CrawledData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crawler.repository.CrawledDataRepository;
import java.util.List;

@Service
public class CrawlerService {
    private final CrawledDataRepository repository;

    @Autowired
    public CrawlerService(CrawledDataRepository repository) {
        this.repository = repository;
    }


    public CrawledData saveCrawledData(String url, String text) {
        CrawledData data = new CrawledData(url, text);
        return repository.save(data); // Saves the data into the database
    }

    public List<CrawledData> findAllCrawledData() {
        return repository.findAll();
    }
}