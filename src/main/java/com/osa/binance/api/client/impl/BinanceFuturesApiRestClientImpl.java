package com.osa.binance.api.client.impl;

import java.util.List;

import com.osa.binance.api.client.BinanceFuturesApiRestClient;
import com.osa.binance.api.client.config.BinanceConfig;
import com.osa.binance.api.client.domain.account.LeverageResponse;
import com.osa.binance.api.client.domain.account.NewOrder;
import com.osa.binance.api.client.domain.account.NewOrderResponse;
import com.osa.binance.api.client.domain.account.PositionRisk;
import com.osa.binance.api.client.domain.account.request.CancelOrderRequest;
import com.osa.binance.api.client.domain.account.request.CancelOrderResponse;
import com.osa.binance.api.client.domain.account.request.ChangeLeverageRequest;
import com.osa.binance.api.client.domain.account.request.PositionRiskRequest;
import com.osa.binance.api.client.domain.general.ExchangeInfo;
import com.osa.binance.api.client.domain.market.Candlestick;
import com.osa.binance.api.client.domain.market.CandlestickInterval;
import lombok.extern.log4j.Log4j2;
import retrofit2.Call;

@Log4j2
public class BinanceFuturesApiRestClientImpl implements BinanceFuturesApiRestClient {

  private final BinanceApiFuturesService binanceApiService;

  public BinanceFuturesApiRestClientImpl(BinanceConfig binanceConfig) {
    binanceApiService = BinanceApiServiceProducer.createService(BinanceApiFuturesService.class, binanceConfig);
  }

  @Override
  public ExchangeInfo getExchangeInfo() {
    return BinanceApiServiceProducer.executeSync(binanceApiService.getExchangeInfo());
  }

  @Override
  public NewOrderResponse newOrder(NewOrder order) {
    final Call<NewOrderResponse> call;
    if (order.getQuoteOrderQty() == null) {
      call = binanceApiService.newOrder(order.getSymbol(), order.getSide(), order.getType(),
          order.getTimeInForce(), order.getQuantity(), order.getPrice(), order.getNewClientOrderId(),
          order.getStopPrice(),  order.getNewOrderRespType(), order.getRecvWindow(),
          order.getTimestamp(), order.isClosePosition());
    } else {
      call = binanceApiService.newOrderQuoteQty(order.getSymbol(), order.getSide(), order.getType(),
          order.getTimeInForce(), order.getQuoteOrderQty(), order.getPrice(), order.getNewClientOrderId(),
          order.getStopPrice(), order.getNewOrderRespType(), order.getRecvWindow(),
          order.getTimestamp());
    }
    NewOrderResponse newOrderResponse = BinanceApiServiceProducer.executeSync(call);
    log.info("Order has been placed " + newOrderResponse);
    return newOrderResponse;
  }

  @Override
  public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
    CancelOrderResponse cancelOrderResponse = BinanceApiServiceProducer.executeSync(
        binanceApiService.cancelOrder(cancelOrderRequest.getSymbol(), cancelOrderRequest.getOrderId(),
            cancelOrderRequest.getOrigClientOrderId(), cancelOrderRequest.getNewClientOrderId(),
            cancelOrderRequest.getRecvWindow(), cancelOrderRequest.getTimestamp()));
    log.info("Order has been canceled " + cancelOrderResponse);
    return cancelOrderResponse;
  }

  @Override
  public List<PositionRisk> getPositionRisk(PositionRiskRequest positionRiskRequest) {
    return BinanceApiServiceProducer.executeSync(
        binanceApiService.getPositionRisk(positionRiskRequest.getSymbol(), positionRiskRequest.getRecvWindow(),
            positionRiskRequest.getTimestamp()));
  }

  @Override
  public LeverageResponse changeInitialLeverage(ChangeLeverageRequest changeLeverageRequest) {
    return BinanceApiServiceProducer.executeSync(
        binanceApiService.changeInitialLeverage(changeLeverageRequest.getLeverage(), changeLeverageRequest.getSymbol(),
            changeLeverageRequest.getRecvWindow(),
            changeLeverageRequest.getTimestamp()));
  }

  @Override
  public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit,
                                              Long startTime, Long endTime) {
    return BinanceApiServiceProducer.executeSync(
        binanceApiService.getCandlestickBars(symbol, interval.getIntervalId(), limit, startTime, endTime));
  }
}
