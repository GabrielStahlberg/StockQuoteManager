package com.stockquotemanager.stockquotemanager.model;

import com.stockquotemanager.stockquotemanager.model.dto.QuoteDTO;
import com.stockquotemanager.stockquotemanager.model.dto.StockQuotesDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbstock_quotes")
public class StockQuotes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "stockquotes_id")
    private String id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Quote> quotes;

    @Column()
    private Stock stock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public StockQuotesDTO toDTO() {
        List<QuoteDTO> quoteDTOS = new ArrayList<>();
        for(Quote item : this.quotes) {
            quoteDTOS.add(item.toDTO());
        }
        return new StockQuotesDTO(this.id, quoteDTOS);
    }
}
