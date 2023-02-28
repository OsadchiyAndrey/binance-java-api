package com.osa.binance.api.client.impl;

import com.osa.binance.api.client.BinanceApiRestClient;
import com.osa.binance.api.client.config.BinanceConfig;
import com.osa.binance.api.client.constant.BinanceApiConstants;
import com.osa.binance.api.client.domain.general.Asset;
import com.osa.binance.api.client.domain.general.ExchangeInfo;
import com.osa.binance.api.client.domain.account.Account;
import com.osa.binance.api.client.domain.account.DepositAddress;
import com.osa.binance.api.client.domain.account.DepositHistory;
import com.osa.binance.api.client.domain.account.DustTransferResponse;
import com.osa.binance.api.client.domain.account.NewOCO;
import com.osa.binance.api.client.domain.account.NewOCOResponse;
import com.osa.binance.api.client.domain.account.NewOrder;
import com.osa.binance.api.client.domain.account.NewOrderResponse;
import com.osa.binance.api.client.domain.account.Order;
import com.osa.binance.api.client.domain.account.OrderList;
import com.osa.binance.api.client.domain.account.SubAccountTransfer;
import com.osa.binance.api.client.domain.account.Trade;
import com.osa.binance.api.client.domain.account.TradeHistoryItem;
import com.osa.binance.api.client.domain.account.WithdrawHistory;
import com.osa.binance.api.client.domain.account.WithdrawResult;
import com.osa.binance.api.client.domain.account.request.AllOrderListRequest;
import com.osa.binance.api.client.domain.account.request.AllOrdersRequest;
import com.osa.binance.api.client.domain.account.request.CancelOrderListRequest;
import com.osa.binance.api.client.domain.account.request.CancelOrderListResponse;
import com.osa.binance.api.client.domain.account.request.CancelOrderRequest;
import com.osa.binance.api.client.domain.account.request.CancelOrderResponse;
import com.osa.binance.api.client.domain.account.request.OrderListStatusRequest;
import com.osa.binance.api.client.domain.account.request.OrderRequest;
import com.osa.binance.api.client.domain.account.request.OrderStatusRequest;
import com.osa.binance.api.client.domain.market.AggTrade;
import com.osa.binance.api.client.domain.market.BookTicker;
import com.osa.binance.api.client.domain.market.Candlestick;
import com.osa.binance.api.client.domain.market.CandlestickInterval;
import com.osa.binance.api.client.domain.market.OrderBook;
import com.osa.binance.api.client.domain.market.TickerPrice;
import com.osa.binance.api.client.domain.market.TickerStatistics;
import retrofit2.Call;

import java.util.List;

import static com.osa.binance.api.client.impl.BinanceApiServiceProducer.createService;

/**
 * Implementation of Binance's REST API using Retrofit with synchronous/blocking
 * method calls.
 */
public class BinanceApiRestClientImpl implements BinanceApiRestClient {

	private final BinanceApiService binanceApiService;

	public BinanceApiRestClientImpl(BinanceConfig binanceConfig) {
		binanceApiService = BinanceApiServiceProducer.createService(BinanceApiService.class, binanceConfig);
	}

	// General endpoints

	@Override
	public void ping() {
		BinanceApiServiceProducer.executeSync(binanceApiService.ping());
	}

	@Override
	public Long getServerTime() {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getServerTime()).getServerTime();
	}

	@Override
	public ExchangeInfo getExchangeInfo() {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getExchangeInfo());
	}

	@Override
	public List<Asset> getAllAssets() {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getAllAssets(BinanceApiConstants.BINANCE_ASSETS_URL));
	}

	// Market Data endpoints

	@Override
	public OrderBook getOrderBook(String symbol, Integer limit) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getOrderBook(symbol, limit));
	}

	@Override
	public List<TradeHistoryItem> getTrades(String symbol, Integer limit) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getTrades(symbol, limit));
	}

	@Override
	public List<TradeHistoryItem> getHistoricalTrades(String symbol, Integer limit, Long fromId) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getHistoricalTrades(symbol, limit, fromId));
	}

	@Override
	public List<AggTrade> getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getAggTrades(symbol, fromId, limit, startTime, endTime));
	}

	@Override
	public List<AggTrade> getAggTrades(String symbol) {
		return getAggTrades(symbol, null, null, null, null);
	}

	@Override
	public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit,
												Long startTime, Long endTime) {
		return BinanceApiServiceProducer.executeSync(
				binanceApiService.getCandlestickBars(symbol, interval.getIntervalId(), limit, startTime, endTime));
	}

	@Override
	public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval) {
		return getCandlestickBars(symbol, interval, null, null, null);
	}

	@Override
	public TickerStatistics get24HrPriceStatistics(String symbol) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.get24HrPriceStatistics(symbol));
	}

	@Override
	public List<TickerStatistics> getAll24HrPriceStatistics() {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getAll24HrPriceStatistics());
	}

	@Override
	public TickerPrice getPrice(String symbol) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getLatestPrice(symbol));
	}

	@Override
	public List<TickerPrice> getAllPrices() {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getLatestPrices());
	}

	@Override
	public List<BookTicker> getBookTickers() {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getBookTickers());
	}

	@Override
	public NewOrderResponse newOrder(NewOrder order) {
		final Call<NewOrderResponse> call;
		if (order.getQuoteOrderQty() == null) {
			call = binanceApiService.newOrder(order.getSymbol(), order.getSide(), order.getType(),
					order.getTimeInForce(), order.getQuantity(), order.getPrice(), order.getNewClientOrderId(),
					order.getStopPrice(),  order.getNewOrderRespType(), order.getRecvWindow(),
					order.getTimestamp());
		} else {
			call = binanceApiService.newOrderQuoteQty(order.getSymbol(), order.getSide(), order.getType(),
					order.getTimeInForce(), order.getQuoteOrderQty(), order.getPrice(), order.getNewClientOrderId(),
					order.getStopPrice(), order.getNewOrderRespType(), order.getRecvWindow(),
					order.getTimestamp());
		}
		return BinanceApiServiceProducer.executeSync(call);
	}

	@Override
	public void newOrderTest(NewOrder order) {
		BinanceApiServiceProducer.executeSync(binanceApiService.newOrderTest(order.getSymbol(), order.getSide(), order.getType(),
				order.getTimeInForce(), order.getQuantity(), order.getPrice(), order.getNewClientOrderId(),
				order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(), order.getRecvWindow(),
				order.getTimestamp()));
	}

	// Account endpoints

	@Override
	public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getOrderStatus(orderStatusRequest.getSymbol(),
				orderStatusRequest.getOrderId(), orderStatusRequest.getOrigClientOrderId(),
				orderStatusRequest.getRecvWindow(), orderStatusRequest.getTimestamp()));
	}

	@Override
	public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
		return BinanceApiServiceProducer.executeSync(
				binanceApiService.cancelOrder(cancelOrderRequest.getSymbol(), cancelOrderRequest.getOrderId(),
						cancelOrderRequest.getOrigClientOrderId(), cancelOrderRequest.getNewClientOrderId(),
						cancelOrderRequest.getRecvWindow(), cancelOrderRequest.getTimestamp()));
	}

	@Override
	public List<Order> getOpenOrders(OrderRequest orderRequest) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getOpenOrders(orderRequest.getSymbol(), orderRequest.getRecvWindow(),
				orderRequest.getTimestamp()));
	}

	@Override
	public List<Order> getAllOrders(AllOrdersRequest orderRequest) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getAllOrders(orderRequest.getSymbol(), orderRequest.getOrderId(),
				orderRequest.getLimit(), orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
	}

	@Override
	public NewOCOResponse newOCO(NewOCO oco) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.newOCO(oco.getSymbol(), oco.getListClientOrderId(), oco.getSide(),
				oco.getQuantity(), oco.getLimitClientOrderId(), oco.getPrice(), oco.getLimitIcebergQty(),
				oco.getStopClientOrderId(), oco.getStopPrice(), oco.getStopLimitPrice(), oco.getStopIcebergQty(),
				oco.getStopLimitTimeInForce(), oco.getNewOrderRespType(), oco.getRecvWindow(), oco.getTimestamp()));
	}

	@Override
	public CancelOrderListResponse cancelOrderList(CancelOrderListRequest cancelOrderListRequest) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.cancelOrderList(cancelOrderListRequest.getSymbol(), cancelOrderListRequest.getOrderListId(),
				cancelOrderListRequest.getListClientOrderId(), cancelOrderListRequest.getNewClientOrderId(),
				cancelOrderListRequest.getRecvWindow(), cancelOrderListRequest.getTimestamp()));
	}

	@Override
	public OrderList getOrderListStatus(OrderListStatusRequest orderListStatusRequest) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getOrderListStatus(orderListStatusRequest.getOrderListId(), orderListStatusRequest.getOrigClientOrderId(),
				orderListStatusRequest.getRecvWindow(), orderListStatusRequest.getTimestamp()));
	}

	@Override
	public List<OrderList> getAllOrderList(AllOrderListRequest allOrderListRequest) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getAllOrderList(allOrderListRequest.getFromId(), allOrderListRequest.getStartTime(),
				allOrderListRequest.getEndTime(), allOrderListRequest.getLimit(), allOrderListRequest.getRecvWindow(), allOrderListRequest.getTimestamp()));
	}

	@Override
	public Account getAccount(Long recvWindow, Long timestamp) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getAccount(recvWindow, timestamp));
	}

	@Override
	public Account getAccount() {
		return getAccount(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
	}

	@Override
	public List<Trade> getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getMyTrades(symbol, limit, fromId, recvWindow, timestamp));
	}

	@Override
	public List<Trade> getMyTrades(String symbol, Integer limit) {
		return getMyTrades(symbol, limit, null, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
				System.currentTimeMillis());
	}

	@Override
	public List<Trade> getMyTrades(String symbol) {
		return getMyTrades(symbol, null, null, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
				System.currentTimeMillis());
	}

	@Override
	public List<Trade> getMyTrades(String symbol, Long fromId) {
		return getMyTrades(symbol, null, fromId, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
				System.currentTimeMillis());
	}

	@Override
	public WithdrawResult withdraw(String asset, String address, String amount, String name, String addressTag) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.withdraw(asset, address, amount, name, addressTag,
				BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
	}

	@Override
	public DustTransferResponse dustTranfer(List<String> asset) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.dustTransfer(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
	}

	@Override
	public DepositHistory getDepositHistory(String asset) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getDepositHistory(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
				System.currentTimeMillis()));
	}

	@Override
	public WithdrawHistory getWithdrawHistory(String asset) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getWithdrawHistory(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
				System.currentTimeMillis()));
	}

	@Override
	public List<SubAccountTransfer> getSubAccountTransfers() {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getSubAccountTransfers(System.currentTimeMillis()));
	}

	@Override
	public DepositAddress getDepositAddress(String asset) {
		return BinanceApiServiceProducer.executeSync(binanceApiService.getDepositAddress(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
				System.currentTimeMillis()));
	}

	// User stream endpoints

	@Override
	public String startUserDataStream() {
		return BinanceApiServiceProducer.executeSync(binanceApiService.startUserDataStream()).toString();
	}

	@Override
	public void keepAliveUserDataStream(String listenKey) {
		BinanceApiServiceProducer.executeSync(binanceApiService.keepAliveUserDataStream(listenKey));
	}

	@Override
	public void closeUserDataStream(String listenKey) {
		BinanceApiServiceProducer.executeSync(binanceApiService.closeAliveUserDataStream(listenKey));
	}
}
