package com.osa.binance.api.client.impl;

import java.util.List;

import com.osa.binance.api.client.constant.BinanceApiConstants;
import com.osa.binance.api.client.domain.OrderSide;
import com.osa.binance.api.client.domain.OrderType;
import com.osa.binance.api.client.domain.TimeInForce;
import com.osa.binance.api.client.domain.account.LeverageResponse;
import com.osa.binance.api.client.domain.account.NewOrderResponse;
import com.osa.binance.api.client.domain.account.NewOrderResponseType;
import com.osa.binance.api.client.domain.account.PositionRisk;
import com.osa.binance.api.client.domain.account.request.CancelOrderResponse;
import com.osa.binance.api.client.domain.general.ExchangeInfo;
import com.osa.binance.api.client.domain.market.Candlestick;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BinanceApiFuturesService {

  @GET("/fapi/v1/exchangeInfo")
  Call<ExchangeInfo> getExchangeInfo();

  @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
  @POST("/fapi/v1/order")
  Call<NewOrderResponse> newOrder(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type")
  OrderType type,
                                  @Query("timeInForce") TimeInForce timeInForce, @Query("quantity") String quantity, @Query("price") String price,
                                  @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
                                  @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                                  @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp, @Query("closePosition") boolean closePosition);

  @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
  @POST("/fapi/v1/order")
  Call<NewOrderResponse> newOrderQuoteQty(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type,
                                          @Query("timeInForce") TimeInForce timeInForce, @Query("quoteOrderQty") String quoteOrderQty, @Query("price") String price,
                                          @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
                                          @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                                          @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

  @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
  @DELETE("/fapi/v1/order")
  Call<CancelOrderResponse> cancelOrder(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                                        @Query("origClientOrderId") String origClientOrderId, @Query("newClientOrderId") String newClientOrderId,
                                        @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

  @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
  @GET("/fapi/v2/positionRisk")
  Call<List<PositionRisk>> getPositionRisk(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow,
                                           @Query("timestamp") Long timestamp);

  @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
  @POST("/fapi/v1/leverage")
  Call<LeverageResponse> changeInitialLeverage(@Query("leverage") Integer leverage,
                                               @Query("symbol") String symbol, @Query("recvWindow") Long recvWindow,
                                               @Query("timestamp") Long timestamp);

  @GET("/fapi/v1/klines")
  Call<List<Candlestick>> getCandlestickBars(@Query("symbol") String symbol, @Query("interval") String interval, @Query("limit") Integer limit,
                                             @Query("startTime") Long startTime, @Query("endTime") Long endTime);
}
