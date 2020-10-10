package com.stockquotemanager.stockquotemanager.resources;

import com.stockquotemanager.stockquotemanager.model.StockQuotes;
import com.stockquotemanager.stockquotemanager.model.dto.StockQuotesDTO;
import com.stockquotemanager.stockquotemanager.services.StockQuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class StockQuotesResource {

    @Autowired
    private StockQuotesService service;

    @GetMapping("stockquote")
    public ResponseEntity<List<StockQuotesDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("stockquote/{id}")
    public ResponseEntity<StockQuotesDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping("stockquote")
    public ResponseEntity persist(@RequestBody StockQuotes stockQuotes) {
        StockQuotes stockQuotesSaved = service.save(stockQuotes);
        if(null != stockQuotesSaved) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stockQuotes.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This Stock doesn't exist.");
    }

    @DeleteMapping("stockcache")
    public ResponseEntity<Void> cleanCache() {
        service.cleanCache();
        return ResponseEntity.ok().build();
    }
}
