package com.osa.binance.api.client;

import java.io.Closeable;

import com.osa.binance.api.client.domain.event.CandlestickEvent;
import com.osa.binance.api.client.domain.event.MarkPriceEvent;
import com.osa.binance.api.client.domain.event.SymbolBookTickerEvent;
import com.osa.binance.api.client.domain.event.SymbolTickerEvent;
import com.osa.binance.api.client.domain.market.CandlestickInterval;

public interface BinanceFuturesWebSocketClient extends Closeable {

  Closeable onMarkPriceEvent(String symbol, BinanceApiCallback<MarkPriceEvent> callback);
  Closeable onSymbolTickerEvent(String symbol, BinanceApiCallback<SymbolTickerEvent> callback);
  Closeable onSymbolBookTickerEvent(String symbol, BinanceApiCallback<SymbolBookTickerEvent> callback);
  Closeable onCandlestickEvent(String symbols, CandlestickInterval interval, BinanceApiCallback<CandlestickEvent> callback);

}
