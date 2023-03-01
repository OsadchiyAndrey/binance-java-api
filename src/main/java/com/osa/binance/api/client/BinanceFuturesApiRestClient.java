package com.osa.binance.api.client;

import java.util.List;

import com.osa.binance.api.client.domain.account.LeverageResponse;
import com.osa.binance.api.client.domain.account.NewOrder;
import com.osa.binance.api.client.domain.account.NewOrderResponse;
import com.osa.binance.api.client.domain.account.PositionRisk;
import com.osa.binance.api.client.domain.account.request.CancelOrderRequest;
import com.osa.binance.api.client.domain.account.request.CancelOrderResponse;
import com.osa.binance.api.client.domain.account.request.ChangeLeverageRequest;
import com.osa.binance.api.client.domain.account.request.PositionRiskRequest;
import com.osa.binance.api.client.domain.general.ExchangeInfo;

public interface BinanceFuturesApiRestClient {

  ExchangeInfo getExchangeInfo();

  NewOrderResponse newOrder(NewOrder order);

  CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest);

  List<PositionRisk> getPositionRisk(PositionRiskRequest positionRiskRequest);

  LeverageResponse changeInitialLeverage(ChangeLeverageRequest changeLeverageRequest);
}
