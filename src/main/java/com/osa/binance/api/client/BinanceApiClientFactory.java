package com.osa.binance.api.client;

import com.osa.binance.api.client.config.BinanceConfig;
import com.osa.binance.api.client.impl.BinanceApiAsyncMarginRestClientImpl;
import com.osa.binance.api.client.impl.BinanceApiAsyncRestClientImpl;
import com.osa.binance.api.client.impl.BinanceApiFuturesService;
import com.osa.binance.api.client.impl.BinanceApiMarginRestClientImpl;
import com.osa.binance.api.client.impl.BinanceApiRestClientImpl;
import com.osa.binance.api.client.impl.BinanceApiSwapRestClientImpl;
import com.osa.binance.api.client.impl.BinanceApiWebSocketClientImpl;
import com.osa.binance.api.client.impl.BinanceFuturesApiRestClientImpl;

import static com.osa.binance.api.client.impl.BinanceApiServiceProducer.getSharedClient;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * A factory for creating BinanceApi client objects.
 */
public class BinanceApiClientFactory {

  private BinanceConfig binanceConfig;

  public BinanceApiClientFactory() {
  }

  public BinanceApiClientFactory(BinanceConfig binanceConfig) {
    this.binanceConfig = binanceConfig;
  }

  public static BinanceApiClientFactory getInstance(String apiKey, String secret, boolean useTestnet) {
    BinanceConfig binanceConfig = new BinanceConfig(apiKey, secret, useTestnet);
    return new BinanceApiClientFactory(binanceConfig);
  }

  /**
   * New instance without authentication.
   *
   * @return the binance api client factory
   */
  public static BinanceApiClientFactory newInstance(boolean useTestNet) {
    return new BinanceApiClientFactory(new BinanceConfig(EMPTY, EMPTY, useTestNet));
  }

  /**
   * Creates a new synchronous/blocking REST client.
   */
  public BinanceApiRestClient newRestClient() {
    return new BinanceApiRestClientImpl(binanceConfig);
  }

  public BinanceFuturesApiRestClient newBinanceFuturesApiRestClient() {
    return new BinanceFuturesApiRestClientImpl(binanceConfig);
  }

  /**
   * Creates a new asynchronous/non-blocking REST client.
   */
  public BinanceApiAsyncRestClient newAsyncRestClient() {
    return new BinanceApiAsyncRestClientImpl(binanceConfig);
  }

  /**
   * Creates a new asynchronous/non-blocking Margin REST client.
   */
  public BinanceApiAsyncMarginRestClient newAsyncMarginRestClient() {
    return new BinanceApiAsyncMarginRestClientImpl(binanceConfig);
  }

  /**
   * Creates a new synchronous/blocking Margin REST client.
   */
  public BinanceApiMarginRestClient newMarginRestClient() {
    return new BinanceApiMarginRestClientImpl(binanceConfig);
  }

  /**
   * Creates a new web socket client used for handling data streams.
   */
  public BinanceApiWebSocketClient newWebSocketClient() {
    return new BinanceApiWebSocketClientImpl(getSharedClient(), binanceConfig);
  }

  /**
   * Creates a new synchronous/blocking Swap REST client.
   */
  public BinanceApiSwapRestClient newSwapRestClient() {
    return new BinanceApiSwapRestClientImpl(binanceConfig);
  }
}
