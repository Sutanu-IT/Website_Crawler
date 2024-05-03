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

@RestController  // Indicates that this class serves the role of a controller in Spring MVC
@CrossOrigin(origins = "http://localhost:3000") // Allows CORS requests only from the React app running at http://localhost:3000
@RequestMapping("/api")  // Defines the base URI for all endpoints in this controller
public class WebCrawlerController {

    private final RestTemplate restTemplate;  // RestTemplate is used for consuming RESTful services
    private final CrawlerService CrawlerService;  // Autowired service to handle business logic

    // Constructor injection for RestTemplate and CrawlerService
    @Autowired
    public WebCrawlerController(RestTemplate restTemplate, CrawlerService CrawlerService) {
        this.restTemplate = restTemplate;
        this.CrawlerService = CrawlerService;
    }

    // Handler method for the "/api/crawl" endpoint
    @GetMapping("/crawl")
    public ResponseEntity<String> crawlWebsite(@RequestParam String url) {
        String apiUrl = "http://fastapi-fastapi-chart:8000/crawl/";  // URL of the FastAPI service
        // in this case I used k8s cluter service name in case of localhosting use suitable one
        HttpHeaders headers = new HttpHeaders();  // HTTP headers for the request
        headers.setContentType(MediaType.APPLICATION_JSON);  // Setting the Content-Type to JSON

        // Creating an HTTP entity with the URL as JSON data
        HttpEntity<String> entity = new HttpEntity<>("{\"url\":\"" + url + "\"}", headers);

        try {
            // Sending a POST request to the FastAPI service
            String response = restTemplate.postForObject(apiUrl, entity, String.class);
            // Parsing the response JSON
            JSONObject jsonResponse = new JSONObject(response);
            // Extracting the text content from the response JSON
            String text = jsonResponse.getString("text");
            // Saving the crawled data to the database
            CrawledData data = CrawlerService.saveCrawledData(url, text);
            return ResponseEntity.ok(response);  // Returning the response from the FastAPI service
        } catch (HttpClientErrorException e) {
            // Handling client errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body("Client error: " + e.getMessage());
        } catch (RestClientException e) {
            // Handling other REST client errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e.getMessage());
        } catch (JSONException e) {
            // Handling JSON parsing errors
            throw new RuntimeException(e);
        }
    }

    // Handler method for the "/api/crawled-data" endpoint
    @GetMapping("/crawled-data")
    public ResponseEntity<List<CrawledData>> getAllCrawledData() {
        // Retrieving all crawled data from the database
        List<CrawledData> data = CrawlerService.findAllCrawledData();
        return ResponseEntity.ok(data);  // Returning the list of crawled data
    }
}
