package com.osa.binance.api.client.domain.account.request;

import com.osa.binance.api.client.constant.BinanceApiConstants;

public class PositionRiskRequest {

  String symbol;
  Long recvWindow;
  Long timestamp;

  public PositionRiskRequest(String symbol) {
    this.symbol = symbol;
    this.timestamp = System.currentTimeMillis();
    this.recvWindow = BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Long getRecvWindow() {
    return recvWindow;
  }

  public void setRecvWindow(Long recvWindow) {
    this.recvWindow = recvWindow;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }
}
