package com.stockquotemanager.stockquotemanager.services;

import com.stockquotemanager.stockquotemanager.model.Notification;
import com.stockquotemanager.stockquotemanager.model.StockQuotes;
import com.stockquotemanager.stockquotemanager.repository.StockQuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StockQuotesService {

    private static List<LinkedHashMap<String, String>> allStocks = new ArrayList<>();

    @Autowired
    private StockQuotesRepository repository;

    @Autowired
    private StockManagerService managerService;

    @PostConstruct
    public void retrieveAllStock() {
        managerService.saveNotification(new Notification("localhost", "8081"));
    }

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
        if(allStocks.isEmpty()) {
            allStocks = managerService.findAllStocks();
        }
        return allStocks.stream().anyMatch(item -> item.containsValue(stockId));
    }

    public void cleanCache() {
        allStocks.clear();
    }
}
