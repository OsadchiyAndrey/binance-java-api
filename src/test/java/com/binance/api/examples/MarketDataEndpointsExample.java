package com.binance.api.examples;

import com.osa.binance.api.client.BinanceApiClientFactory;
import com.osa.binance.api.client.BinanceApiRestClient;
import com.osa.binance.api.client.domain.market.AggTrade;
import com.osa.binance.api.client.domain.market.BookTicker;
import com.osa.binance.api.client.domain.market.Candlestick;
import com.osa.binance.api.client.domain.market.CandlestickInterval;
import com.osa.binance.api.client.domain.market.OrderBook;
import com.osa.binance.api.client.domain.market.TickerPrice;
import com.osa.binance.api.client.domain.market.TickerStatistics;
import com.osa.binance.api.client.exception.BinanceApiException;

import java.util.List;

/**
 * Examples on how to get market data information such as the latest price of a symbol, etc.
 */
public class MarketDataEndpointsExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(true);
    BinanceApiRestClient client = factory.newRestClient();

    // Getting depth of a symbol
    OrderBook orderBook = client.getOrderBook("NEOETH", 10);
    System.out.println(orderBook.getAsks());

    // Getting latest price of a symbol
    TickerStatistics tickerStatistics = client.get24HrPriceStatistics("NEOETH");
    System.out.println(tickerStatistics);

    // Getting all latest prices
    List<TickerPrice> allPrices = client.getAllPrices();
    System.out.println(allPrices);

    // Getting agg trades
    List<AggTrade> aggTrades = client.getAggTrades("NEOETH");
    System.out.println(aggTrades);

    // Weekly candlestick bars for a symbol
    List<Candlestick> candlesticks = client.getCandlestickBars("NEOETH", CandlestickInterval.WEEKLY);
    System.out.println(candlesticks);

    // Getting all book tickers
    List<BookTicker> allBookTickers = client.getBookTickers();
    System.out.println(allBookTickers);

    // Exception handling
    try {
      client.getOrderBook("UNKNOWN", 10);
    } catch (BinanceApiException e) {
      System.out.println(e.getError().getCode()); // -1121
      System.out.println(e.getError().getMsg());  // Invalid symbol
    }
  }
}
