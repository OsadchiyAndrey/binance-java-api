package com.binance.api.examples;

import com.osa.binance.api.client.BinanceApiAsyncRestClient;
import com.osa.binance.api.client.BinanceApiClientFactory;
import com.osa.binance.api.client.domain.TimeInForce;
import com.osa.binance.api.client.domain.account.request.AllOrdersRequest;
import com.osa.binance.api.client.domain.account.request.CancelOrderRequest;
import com.osa.binance.api.client.domain.account.request.OrderRequest;
import com.osa.binance.api.client.domain.account.request.OrderStatusRequest;

import static com.osa.binance.api.client.domain.account.NewOrder.limitBuy;
import static com.osa.binance.api.client.domain.account.NewOrder.marketBuy;

/**
 * Examples on how to place orders, cancel them, and query account information.
 */
public class OrdersExampleAsync {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.getInstance("YOUR_API_KEY", "YOUR_SECRET", false);
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

    // Getting list of open orders
    client.getOpenOrders(new OrderRequest("LINKETH"), response -> System.out.println(response));

    // Get status of a particular order
    client.getOrderStatus(new OrderStatusRequest("LINKETH", 745262L),
        response -> System.out.println(response));

    // Getting list of all orders with a limit of 10
    client.getAllOrders(new AllOrdersRequest("LINKETH").limit(10), response -> System.out.println(response));

    // Canceling an order
    client.cancelOrder(new CancelOrderRequest("LINKETH", 756703L),
        response -> System.out.println(response));

    // Placing a test LIMIT order
    client.newOrderTest(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"),
        response -> System.out.println("Test order has succeeded."));

    // Placing a test MARKET order
    client.newOrderTest(marketBuy("LINKETH", "1000"), response -> System.out.println("Test order has succeeded."));

    // Placing a real LIMIT order
    client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"),
        response -> System.out.println(response));
  }
}
