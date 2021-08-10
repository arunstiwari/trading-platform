package com.tekmentor.stockservice.repository;

import com.tekmentor.stockservice.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,String> {
    Optional<Stock> findByTickerName(String tickerName);
}
