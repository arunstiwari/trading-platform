import React, {useEffect} from 'react';
import StockService from '../../services/stockservice';

const Stocks = () => {
    useEffect(() => {
        StockService.fetchStocks();
    },[])
    return (
        <div>
            <h1>Stocks Page</h1>
        </div>
    );
};

export default Stocks;