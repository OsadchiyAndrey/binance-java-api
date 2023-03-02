package com.binance.api.examples;

import com.osa.binance.api.client.BinanceApiClientFactory;
import com.osa.binance.api.client.BinanceFuturesWebSocketClient;

public class TickerEventExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(true);
    BinanceFuturesWebSocketClient binanceFuturesWebSocketClient = factory.newFuturesWebSocketClient();

    binanceFuturesWebSocketClient.onSymbolBookTickerEvent("btcusdt", event -> {
      System.out.println(event);
    });
  }
}
