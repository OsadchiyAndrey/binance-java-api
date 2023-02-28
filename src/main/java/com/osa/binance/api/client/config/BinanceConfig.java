package com.osa.binance.api.client.config;

import com.osa.binance.api.client.constant.BinanceApiConstants;

public class BinanceConfig {

  private String accessKey;
  private String secret;
  private String apiUrl;
  private String webSocketUrl;

  public BinanceConfig(String accessKey, String secret, boolean isTestNet) {
    this.accessKey = accessKey;
    this.secret = secret;
    if (isTestNet) {
      this.apiUrl = BinanceApiConstants.TESTNET_API_URL;
      this.webSocketUrl =  BinanceApiConstants.TESTNET_WEBSOCKET_URL;
    } else {
      this.apiUrl = BinanceApiConstants.API_URL;
      this.webSocketUrl =  BinanceApiConstants.WEBSOCKET_URL;
    }
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSecret() {
    return secret;
  }

  public String getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  public String getWebSocketUrl() {
    return webSocketUrl;
  }

  public void setWebSocketUrl(String webSocketUrl) {
    this.webSocketUrl = webSocketUrl;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }
}
