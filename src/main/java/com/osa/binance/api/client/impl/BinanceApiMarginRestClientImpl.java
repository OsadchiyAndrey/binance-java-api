package com.osa.binance.api.client.impl;

import com.osa.binance.api.client.BinanceApiMarginRestClient;
import com.osa.binance.api.client.config.BinanceConfig;
import com.osa.binance.api.client.constant.BinanceApiConstants;
import com.osa.binance.api.client.domain.TransferType;
import com.osa.binance.api.client.domain.account.request.CancelOrderRequest;
import com.osa.binance.api.client.domain.account.request.CancelOrderResponse;
import com.osa.binance.api.client.domain.account.request.OrderRequest;
import com.osa.binance.api.client.domain.account.request.OrderStatusRequest;
import com.osa.binance.api.client.domain.account.LoanQueryResult;
import com.osa.binance.api.client.domain.account.MarginAccount;
import com.osa.binance.api.client.domain.account.MarginNewOrder;
import com.osa.binance.api.client.domain.account.MarginNewOrderResponse;
import com.osa.binance.api.client.domain.account.MarginTransaction;
import com.osa.binance.api.client.domain.account.MaxBorrowableQueryResult;
import com.osa.binance.api.client.domain.account.Order;
import com.osa.binance.api.client.domain.account.RepayQueryResult;
import com.osa.binance.api.client.domain.account.Trade;

import java.util.List;

/**
 * Implementation of Binance's Margin REST API using Retrofit with asynchronous/non-blocking method calls.
 */
public class BinanceApiMarginRestClientImpl implements BinanceApiMarginRestClient {

    private final BinanceApiService binanceApiService;

    public BinanceApiMarginRestClientImpl(BinanceConfig binanceConfig) {
        binanceApiService = BinanceApiServiceProducer.createService(BinanceApiService.class, binanceConfig);
    }

    @Override
    public MarginAccount getAccount() {
        long timestamp = System.currentTimeMillis();
        return BinanceApiServiceProducer.executeSync(binanceApiService.getMarginAccount(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public List<Order> getOpenOrders(OrderRequest orderRequest) {
        return BinanceApiServiceProducer.executeSync(binanceApiService.getOpenMarginOrders(orderRequest.getSymbol(), orderRequest.getRecvWindow(),
                orderRequest.getTimestamp()));
    }

    @Override
    public MarginNewOrderResponse newOrder(MarginNewOrder order) {
        return BinanceApiServiceProducer.executeSync(binanceApiService.newMarginOrder(order.getSymbol(), order.getSide(), order.getType(),
                order.getTimeInForce(), order.getQuantity(), order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(),
                order.getIcebergQty(), order.getNewOrderRespType(), order.getSideEffectType(), order.getRecvWindow(), order.getTimestamp()));
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
        return BinanceApiServiceProducer.executeSync(binanceApiService.cancelMarginOrder(cancelOrderRequest.getSymbol(),
                cancelOrderRequest.getOrderId(), cancelOrderRequest.getOrigClientOrderId(), cancelOrderRequest.getNewClientOrderId(),
                cancelOrderRequest.getRecvWindow(), cancelOrderRequest.getTimestamp()));
    }

    @Override
    public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
        return BinanceApiServiceProducer.executeSync(binanceApiService.getMarginOrderStatus(orderStatusRequest.getSymbol(),
                orderStatusRequest.getOrderId(), orderStatusRequest.getOrigClientOrderId(),
                orderStatusRequest.getRecvWindow(), orderStatusRequest.getTimestamp()));
    }

    @Override
    public List<Trade> getMyTrades(String symbol) {
        return BinanceApiServiceProducer.executeSync(binanceApiService.getMyTrades(symbol, null, null, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }

    // user stream endpoints

    @Override
    public String startUserDataStream() {
        return BinanceApiServiceProducer.executeSync(binanceApiService.startMarginUserDataStream()).toString();
    }

    @Override
    public void keepAliveUserDataStream(String listenKey) {
        BinanceApiServiceProducer.executeSync(binanceApiService.keepAliveMarginUserDataStream(listenKey));
    }

    @Override
    public MarginTransaction transfer(String asset, String amount, TransferType type) {
        long timestamp = System.currentTimeMillis();
        return BinanceApiServiceProducer.executeSync(binanceApiService.transfer(asset, amount, type.getValue(), BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public MarginTransaction borrow(String asset, String amount) {
        long timestamp = System.currentTimeMillis();
        return BinanceApiServiceProducer.executeSync(binanceApiService.borrow(asset, amount, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public LoanQueryResult queryLoan(String asset, String txId) {
        long timestamp = System.currentTimeMillis();
        return BinanceApiServiceProducer.executeSync(binanceApiService.queryLoan(asset, txId, timestamp));
    }

    @Override
    public RepayQueryResult queryRepay(String asset, String txId) {
        long timestamp = System.currentTimeMillis();
        return BinanceApiServiceProducer.executeSync(binanceApiService.queryRepay(asset, txId, timestamp));
    }

    @Override
    public RepayQueryResult queryRepay(String asset, long startTime) {
        long timestamp = System.currentTimeMillis();
        return BinanceApiServiceProducer.executeSync(binanceApiService.queryRepay(asset, startTime, timestamp));
    }

    @Override
    public MaxBorrowableQueryResult queryMaxBorrowable(String asset) {
        long timestamp = System.currentTimeMillis();
        return BinanceApiServiceProducer.executeSync(binanceApiService.queryMaxBorrowable(asset, timestamp));
    }

    @Override
    public MarginTransaction repay(String asset, String amount) {
        long timestamp = System.currentTimeMillis();
        return BinanceApiServiceProducer.executeSync(binanceApiService.repay(asset, amount, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, timestamp));
    }
}