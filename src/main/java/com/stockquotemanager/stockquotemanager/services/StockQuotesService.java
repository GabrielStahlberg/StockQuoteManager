package com.stockquotemanager.stockquotemanager.services;

import com.stockquotemanager.stockquotemanager.model.Notification;
import com.stockquotemanager.stockquotemanager.model.StockQuotes;
import com.stockquotemanager.stockquotemanager.model.dto.StockQuotesDTO;
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

    public List<StockQuotesDTO> findAll() {
        List<StockQuotes> stockQuotes = repository.findAll();
        List<StockQuotesDTO> stockQuotesDTO = new ArrayList<>();

        for (StockQuotes item : stockQuotes) {
            stockQuotesDTO.add(item.toDTO());
        }

        return stockQuotesDTO;
    }

    public StockQuotesDTO findById(String id) {
        StockQuotes stockQuotes = repository.findById(id).orElseThrow(() -> new NoSuchElementException("StockQuotes not found"));
        return stockQuotes.toDTO();
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
