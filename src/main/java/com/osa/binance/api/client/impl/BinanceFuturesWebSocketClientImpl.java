package com.osa.binance.api.client.impl;

import java.io.Closeable;
import java.io.IOException;

import com.osa.binance.api.client.BinanceApiCallback;
import com.osa.binance.api.client.BinanceFuturesWebSocketClient;
import com.osa.binance.api.client.config.BinanceConfig;
import com.osa.binance.api.client.domain.event.BookTickerEvent;
import com.osa.binance.api.client.domain.event.MarkPriceEvent;
import com.osa.binance.api.client.domain.event.SymbolBookTickerEvent;
import com.osa.binance.api.client.domain.event.SymbolTickerEvent;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

@Log4j2
public class BinanceFuturesWebSocketClientImpl implements BinanceFuturesWebSocketClient {

  private final OkHttpClient client;
  private final BinanceConfig binanceConfig;

  public BinanceFuturesWebSocketClientImpl(OkHttpClient client, BinanceConfig binanceConfig) {
    this.client = client;
    this.binanceConfig = binanceConfig;
  }

  @Override
  public Closeable onMarkPriceEvent(String symbol, BinanceApiCallback<MarkPriceEvent> callback) {
    final String channel = symbol + "@markPrice";
    return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, MarkPriceEvent.class));
  }

  @Override
  public Closeable onSymbolTickerEvent(String symbol, BinanceApiCallback<SymbolTickerEvent> callback) {
    final String channel = symbol + "@ticker";
    return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, SymbolTickerEvent.class));
  }

  @Override
  public Closeable onSymbolBookTickerEvent(String symbol, BinanceApiCallback<SymbolBookTickerEvent> callback) {
    final String channel = symbol + "@bookTicker";
    return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, SymbolBookTickerEvent.class));
  }

  @Override
  public void close() throws IOException {
  }

  private Closeable createNewWebSocket(String channel, BinanceApiWebSocketListener<?> listener) {
    String streamingUrl = String.format("%s/%s", binanceConfig.getWebSocketUrl(), channel);
    Request request = new Request.Builder().url(streamingUrl).build();
    log.info("Connecting to channel " + channel);
    final WebSocket webSocket = client.newWebSocket(request, listener);
    log.info("Successfully connected to channel " + channel);
    return () -> {
      final int code = 1000;
      log.info("Connection close for channel " + channel);
      listener.onClosing(webSocket, code, null);
      webSocket.close(code, null);
      listener.onClosed(webSocket, code, null);
    };
  }

}
