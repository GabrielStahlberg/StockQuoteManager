package com.stockquotemanager.stockquotemanager.repository;

import com.stockquotemanager.stockquotemanager.model.StockQuotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockQuotesRepository extends JpaRepository<StockQuotes, String> {
}
