package com.osa.binance.api.client.domain.general;

public class BookTickerResponse {
  private String symbol;
  private String bidPrice;
  private String bidQty;
  private String askPrice;
  private String askQty;
  private Long time;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(String bidPrice) {
    this.bidPrice = bidPrice;
  }

  public String getBidQty() {
    return bidQty;
  }

  public void setBidQty(String bidQty) {
    this.bidQty = bidQty;
  }

  public String getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(String askPrice) {
    this.askPrice = askPrice;
  }

  public String getAskQty() {
    return askQty;
  }

  public void setAskQty(String askQty) {
    this.askQty = askQty;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "TickerResponse{" +
        "symbol='" + symbol + '\'' +
        ", bidPrice='" + bidPrice + '\'' +
        ", bidQty='" + bidQty + '\'' +
        ", askPrice='" + askPrice + '\'' +
        ", askQty='" + askQty + '\'' +
        ", time=" + time +
        '}';
  }
}