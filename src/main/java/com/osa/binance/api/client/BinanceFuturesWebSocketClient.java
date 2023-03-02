package com.osa.binance.api.client;

import java.io.Closeable;

import com.osa.binance.api.client.domain.event.MarkPriceEvent;
import com.osa.binance.api.client.domain.event.SymbolBookTickerEvent;
import com.osa.binance.api.client.domain.event.SymbolTickerEvent;

public interface BinanceFuturesWebSocketClient extends Closeable {

  Closeable onMarkPriceEvent(String symbol, BinanceApiCallback<MarkPriceEvent> callback);
  Closeable onSymbolTickerEvent(String symbol, BinanceApiCallback<SymbolTickerEvent> callback);
  Closeable onSymbolBookTickerEvent(String symbol, BinanceApiCallback<SymbolBookTickerEvent> callback);
}
