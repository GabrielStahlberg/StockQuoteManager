package com.stockquotemanager.stockquotemanager.model;

import java.io.Serializable;

public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String description;

    public Stock(String id) {
        this.id = id;
    }

    public Stock() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
