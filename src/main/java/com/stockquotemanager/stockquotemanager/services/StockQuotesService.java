package com.stockquotemanager.stockquotemanager.services;

import com.stockquotemanager.stockquotemanager.model.StockQuotes;
import com.stockquotemanager.stockquotemanager.repository.StockQuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void save(StockQuotes stockQuotes) {
        repository.save(stockQuotes);
    }
}
