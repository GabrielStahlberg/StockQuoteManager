package com.stockquotemanager.stockquotemanager.services;

import com.stockquotemanager.stockquotemanager.model.StockQuotes;
import com.stockquotemanager.stockquotemanager.repository.StockQuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StockQuotesService {

    @Autowired
    private StockQuotesRepository repository;

    public List<StockQuotes> findAll() {
        return repository.findAll();
    }

    public StockQuotes findById(String id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("StockQuotes not found"));
    }

    public StockQuotes save(StockQuotes stockQuotes) {
        StockQuotes stockQuotesSaved = null;
        if(isStockRegistered(stockQuotes.getStock().getId())) {
            stockQuotesSaved = repository.save(stockQuotes);
        }
        return stockQuotesSaved;
    }

    private boolean isStockRegistered(String stockId) {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, String>> stockList = restTemplate.getForObject("http://localhost:8080/stock", List.class);
        return stockList.stream().anyMatch(item -> item.containsValue(stockId));
    }
}
