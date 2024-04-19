package com.example.crawler.controller;

import com.example.crawler.model.CrawledData;
import com.example.crawler.services.CrawlerService;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allows CORS requests only from the React app
@RequestMapping("/api")
public class WebCrawlerController {

    private final RestTemplate restTemplate;
    private final CrawlerService CrawlerService;

    @Autowired
    public WebCrawlerController(RestTemplate restTemplate, CrawlerService CrawlerService) {
        this.restTemplate = restTemplate;
        this.CrawlerService = CrawlerService;
    }

    @GetMapping("/crawl")
    public ResponseEntity<String> crawlWebsite(@RequestParam String url) {
        String apiUrl = "http://localhost:8000/crawl/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("{\"url\":\"" + url + "\"}", headers);

        try {
            // Assuming your FastAPI is expecting a POST request
            String response = restTemplate.postForObject(apiUrl, entity, String.class);
            JSONObject jsonResponse = new JSONObject(response);
            String text = jsonResponse.getString("text");  // Adjust based on actual JSON structure.

            CrawledData data = CrawlerService.saveCrawledData(url, text);
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Client error: " + e.getMessage());
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/crawled-data")
    public ResponseEntity<List<CrawledData>> getAllCrawledData() {
        List<CrawledData> data = CrawlerService.findAllCrawledData();
        return ResponseEntity.ok(data);
    }
}