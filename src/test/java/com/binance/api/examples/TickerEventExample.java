package com.binance.api.examples;

import com.osa.binance.api.client.BinanceApiClientFactory;
import com.osa.binance.api.client.BinanceApiWebSocketClient;

public class TickerEventExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(true);
    BinanceApiWebSocketClient client = factory.newWebSocketClient();

    client.onBookTickerEvent("btcusdt", event -> {
      System.out.println(event);

    });
  }
}
