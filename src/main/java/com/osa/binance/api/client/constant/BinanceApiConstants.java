package com.osa.binance.api.client.constant;

import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Constants used throughout Binance's API.
 */
public class BinanceApiConstants {

  public static final String API_URL = "https://fapi.binance.com/";
  public static final String WEBSOCKET_URL = "wss://fstream.binance.com/ws";
  public static final String TESTNET_API_URL = "https://testnet.binancefuture.com/";
  public static final String TESTNET_WEBSOCKET_URL = "wss://stream.binancefuture.com/ws";
  public static final String BINANCE_ASSETS_URL = "https://binance.com/assetWithdraw/getAllAsset.html/";

  /**
   * HTTP Header to be used for API-KEY authentication.
   */
  public static final String API_KEY_HEADER = "X-MBX-APIKEY";

  /**
   * Decorator to indicate that an endpoint requires an API key.
   */
  public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "APIKEY";
  public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";

  /**
   * Decorator to indicate that an endpoint requires a signature.
   */
  public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED";
  public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";

  /**
   * Default receiving window.
   */
  public static final long DEFAULT_RECEIVING_WINDOW = 60_000L;

  /**
   * Default margin receiving window.
   */
  public static final long DEFAULT_MARGIN_RECEIVING_WINDOW = 5_000L;

  /**
   * Default ToStringStyle used by toString methods.
   * Override this to change the output format of the overridden toString methods.
   *  - Example ToStringStyle.JSON_STYLE
   */
  public static ToStringStyle TO_STRING_BUILDER_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;
  public static final String BINANCE_SIGN_PARAM = "signature";
}
