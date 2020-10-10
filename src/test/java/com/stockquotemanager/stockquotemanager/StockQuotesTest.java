package com.stockquotemanager.stockquotemanager;

import com.stockquotemanager.stockquotemanager.model.Quote;
import com.stockquotemanager.stockquotemanager.model.Stock;
import com.stockquotemanager.stockquotemanager.model.StockQuotes;
import com.stockquotemanager.stockquotemanager.repository.StockQuotesRepository;
import com.stockquotemanager.stockquotemanager.services.StockQuotesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockQuotesTest {

    @Autowired
    private StockQuotesService service;

    @Autowired
    private StockQuotesRepository repository;

    @Test
    public void save() {
        Quote quote = new Quote(new Date(), BigDecimal.valueOf(324));
        Stock stock = new Stock("vale5");

        StockQuotes stockQuotes = new StockQuotes("test_1", Arrays.asList(quote), stock);

        Long countBefore = repository.count();
        service.save(stockQuotes);
        Long countAfter = repository.count();

        assertTrue(countAfter > countBefore);
        assertTrue(countAfter == (countBefore+1));
    }

    @Test
    public void findAll() {
        Long count = repository.count();
        assertEquals(count.intValue(), service.findAll().size());
    }

    @Test
    public void findById() {
        Quote quote = new Quote(new Date(), BigDecimal.valueOf(322));
        Stock stock = new Stock("vale5");

        StockQuotes stockQuotes = new StockQuotes("test_2", Arrays.asList(quote), stock);
        service.save(stockQuotes);

        assertEquals(stockQuotes.getId(), service.findById("test_2").getId());
    }
}
