package com.tekmentor.stockservice.service;

import com.tekmentor.stockservice.exception.StockException;
import com.tekmentor.stockservice.model.Stock;
import com.tekmentor.stockservice.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> fetchAllStocks(){
        return stockRepository.findAll();
    }

    public Stock addNewStock(Stock stock){
        Stock save = stockRepository.save(stock);
        return save;
    }

    public Stock searchStock(String tickerName){
        Stock stock = stockRepository.findByTickerName(tickerName)
                .orElseThrow(() -> new StockException("Stock With Name " + tickerName + " not found"));
        return stock;
    }

    public Stock updateStockPrice(String stockId, double price){
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new StockException("Stock with Id: " + stockId + " not found"));
        stock.setPrice(price);
        Stock updatedStock = stockRepository.save(stock);
        return updatedStock;
    }

    public void deleteStock(String id) {
        stockRepository.deleteById(id);
    }
}
