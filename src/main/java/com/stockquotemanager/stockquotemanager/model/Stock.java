package com.stockquotemanager.stockquotemanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbstock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "stock_id")
    private String id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<StockQuotes> stockQuotes;
}
