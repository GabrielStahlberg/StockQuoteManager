package com.stockquotemanager.stockquotemanager.model;

import javax.persistence.*;
import java.io.Serializable;
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

    @ManyToOne
    @JoinColumn(name = "stock_id")
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
}
