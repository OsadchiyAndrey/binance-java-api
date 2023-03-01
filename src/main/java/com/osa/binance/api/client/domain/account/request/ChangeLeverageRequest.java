package com.osa.binance.api.client.domain.account.request;

import com.osa.binance.api.client.constant.BinanceApiConstants;

public class ChangeLeverageRequest {

  private String symbol;
  private Integer leverage;
  Long recvWindow;
  Long timestamp;

  public ChangeLeverageRequest(String symbol, Integer leverage) {
    this.symbol = symbol;
    this.leverage = leverage;
    this.timestamp = System.currentTimeMillis();
    this.recvWindow = BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Integer getLeverage() {
    return leverage;
  }

  public void setLeverage(Integer leverage) {
    this.leverage = leverage;
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
