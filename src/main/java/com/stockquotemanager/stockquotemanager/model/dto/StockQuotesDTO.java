package com.stockquotemanager.stockquotemanager.model.dto;

import java.io.Serializable;
import java.util.List;

public class StockQuotesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private List<QuoteDTO> quotes;

    public StockQuotesDTO(String id, List<QuoteDTO> quotes) {
        this.id = id;
        this.quotes = quotes;
    }

    public StockQuotesDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<QuoteDTO> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<QuoteDTO> quotes) {
        this.quotes = quotes;
    }
}
