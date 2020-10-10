package com.stockquotemanager.stockquotemanager.services;

import com.stockquotemanager.stockquotemanager.model.Notification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class StockManagerService {

    private static final String BASE_URL = "http://externalapp:8080/";
    private RestTemplate restTemplate = new RestTemplate();

    public List<LinkedHashMap<String, String>> findAllStocks() {
        return restTemplate.getForObject(BASE_URL + "stock", List.class);
    }

    public void saveNotification(Notification notification) {
        restTemplate.postForEntity(BASE_URL + "notification", notification, String.class);
    }
}
