package com.osa.binance.api.client.domain.event;

import java.math.BigDecimal;

import com.osa.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SymbolBookTickerEvent {

  @JsonProperty("u")
  private Long orderBookUpdateId;
  @JsonProperty("s")
  private String symbol;
  @JsonProperty("b")
  private BigDecimal bestBidPrice;
  @JsonProperty("B")
  private BigDecimal bestBidQty;
  @JsonProperty("a")
  private BigDecimal bestAskPrice;
  @JsonProperty("A")
  private BigDecimal bestAskQty;

  public Long getOrderBookUpdateId() {
    return orderBookUpdateId;
  }

  public void setOrderBookUpdateId(Long orderBookUpdateId) {
    this.orderBookUpdateId = orderBookUpdateId;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public BigDecimal getBestBidPrice() {
    return bestBidPrice;
  }

  public void setBestBidPrice(BigDecimal bestBidPrice) {
    this.bestBidPrice = bestBidPrice;
  }

  public BigDecimal getBestBidQty() {
    return bestBidQty;
  }

  public void setBestBidQty(BigDecimal bestBidQty) {
    this.bestBidQty = bestBidQty;
  }

  public BigDecimal getBestAskPrice() {
    return bestAskPrice;
  }

  public void setBestAskPrice(BigDecimal bestAskPrice) {
    this.bestAskPrice = bestAskPrice;
  }

  public BigDecimal getBestAskQty() {
    return bestAskQty;
  }

  public void setBestAskQty(BigDecimal bestAskQty) {
    this.bestAskQty = bestAskQty;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("orderBookUpdateId", orderBookUpdateId).append("symbol", symbol)
        .append("bestBidPrice", bestBidPrice).append("bestBidQty", bestBidQty)
        .append("bestAskPrice", bestAskPrice).append("bestAskQty", bestAskQty).toString();
  }
}
