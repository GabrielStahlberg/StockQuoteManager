package com.stockquotemanager.stockquotemanager.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuoteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String date;
    private BigDecimal quotation;

    public QuoteDTO(Date date, BigDecimal quotation) {
        this.date = formatDate(date);
        this.quotation = quotation;
    }

    public void setDate(Date date) {
        this.date = formatDate(date);
    }

    public void setQuotation(BigDecimal quotation) {
        this.quotation = quotation;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getQuotation() {
        return quotation;
    }

    private String formatDate(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(d);
    }
}
