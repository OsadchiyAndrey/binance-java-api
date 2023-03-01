package com.osa.binance.api.client.domain.account;

public class PositionRisk {

  private String entryPrice;
  private String marginType;
  private String isAutoAddMargin;
  private String isolatedMargin;
  private String leverage;
  private String liquidationPrice;
  private String markPrice;
  private String maxNotionalValue;
  private String positionAmt;
  private String notional;
  private String isolatedWallet;
  private String symbol;
  private String unRealizedProfit;
  private String positionSide;
  private Long updateTime;

  public PositionRisk() {
  }

  public String getEntryPrice() {
    return entryPrice;
  }

  public void setEntryPrice(String entryPrice) {
    this.entryPrice = entryPrice;
  }

  public String getMarginType() {
    return marginType;
  }

  public void setMarginType(String marginType) {
    this.marginType = marginType;
  }

  public String getIsAutoAddMargin() {
    return isAutoAddMargin;
  }

  public void setIsAutoAddMargin(String isAutoAddMargin) {
    this.isAutoAddMargin = isAutoAddMargin;
  }

  public String getIsolatedMargin() {
    return isolatedMargin;
  }

  public void setIsolatedMargin(String isolatedMargin) {
    this.isolatedMargin = isolatedMargin;
  }

  public String getLeverage() {
    return leverage;
  }

  public void setLeverage(String leverage) {
    this.leverage = leverage;
  }

  public String getLiquidationPrice() {
    return liquidationPrice;
  }

  public void setLiquidationPrice(String liquidationPrice) {
    this.liquidationPrice = liquidationPrice;
  }

  public String getMarkPrice() {
    return markPrice;
  }

  public void setMarkPrice(String markPrice) {
    this.markPrice = markPrice;
  }

  public String getMaxNotionalValue() {
    return maxNotionalValue;
  }

  public void setMaxNotionalValue(String maxNotionalValue) {
    this.maxNotionalValue = maxNotionalValue;
  }

  public String getPositionAmt() {
    return positionAmt;
  }

  public void setPositionAmt(String positionAmt) {
    this.positionAmt = positionAmt;
  }

  public String getNotional() {
    return notional;
  }

  public void setNotional(String notional) {
    this.notional = notional;
  }

  public String getIsolatedWallet() {
    return isolatedWallet;
  }

  public void setIsolatedWallet(String isolatedWallet) {
    this.isolatedWallet = isolatedWallet;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getUnRealizedProfit() {
    return unRealizedProfit;
  }

  public void setUnRealizedProfit(String unRealizedProfit) {
    this.unRealizedProfit = unRealizedProfit;
  }

  public String getPositionSide() {
    return positionSide;
  }

  public void setPositionSide(String positionSide) {
    this.positionSide = positionSide;
  }

  public Long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }


}
