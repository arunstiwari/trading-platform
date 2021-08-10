package com.tekmentor.stockservice.controller;

import com.tekmentor.stockservice.model.Stock;
import com.tekmentor.stockservice.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    @PreAuthorize("hasAuthority('SCOPE_stocks/read')")
    public ResponseEntity getStocks(Principal user){
        Object details = SecurityContextHolder.getContext().getAuthentication();
        log.info("--details---{}",details);
        log.info("user : {}",user.getName());
        List<Stock> stocks = stockService.fetchAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @PostMapping ("/stocks")
    @PreAuthorize("hasAuthority('SCOPE_stocks/write')")
    public ResponseEntity addNewStock(@RequestBody Stock stock){
         log.info(" stock : {}",stock);
         Stock createdStock = stockService.addNewStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    @DeleteMapping("/stocks/{id}")
    @PreAuthorize("hasAuthority('SCOPE_stocks/delete')")
    public ResponseEntity deleteStock(@PathVariable String id){
         log.info("stock id: {}",id);
         stockService.deleteStock(id);
        return ResponseEntity.status(HttpStatus.OK).body("Stocks deleted successfully");
    }
}
