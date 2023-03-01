package com.osa.binance.api.client.domain.account;

public class LeverageResponse {

  private Long leverage;
  private String maxNotionalValue;
  private String symbol;

  public LeverageResponse() {
  }

  public LeverageResponse(Long leverage, String maxNotionalValue, String symbol) {
    this.leverage = leverage;
    this.maxNotionalValue = maxNotionalValue;
    this.symbol = symbol;
  }

  public Long getLeverage() {
    return leverage;
  }

  public void setLeverage(Long leverage) {
    this.leverage = leverage;
  }

  public String getMaxNotionalValue() {
    return maxNotionalValue;
  }

  public void setMaxNotionalValue(String maxNotionalValue) {
    this.maxNotionalValue = maxNotionalValue;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
