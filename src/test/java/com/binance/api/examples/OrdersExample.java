package com.binance.api.examples;

import com.osa.binance.api.client.BinanceApiClientFactory;
import com.osa.binance.api.client.BinanceApiRestClient;

/**
 * Examples on how to place orders, cancel them, and query account information.
 */
public class OrdersExample {

  public static void main(String[] args) throws InterruptedException {
    BinanceApiClientFactory factory = BinanceApiClientFactory.getInstance("ed68692cb967f609783720a0b47f2c7f8ef3461a6a7f3e2e73daa5c686534583", "5d7da17f75068d4d03c74b3a9f3c070aea7ce08d772c94fea94f1388815ef51a", true);
    BinanceApiRestClient client = factory.newRestClient();
    System.out.println(client.getExchangeInfo());
//    client.newOrder(marketBuy("BTCUSDT", "0.01"));
//    NewOrder btcusdt =
//        new NewOrder("BTCUSDT", OrderSide.SELL, OrderType.TAKE_PROFIT, TimeInForce.GTC, "0.01", "23600.00")
//            .stopPrice("23600");
//    NewOrderResponse newOrderResponse = client.newOrder(btcusdt);
//    Thread.sleep(20000);
//    client.cancelOrder(new CancelOrderRequest(newOrderResponse.getSymbol(), newOrderResponse.getOrderId()));
    System.out.println(client);

//    // Getting list of open orders
//    List<Order> openOrders = client.getOpenOrders(new OrderRequest("LINKETH"));
//    System.out.println(openOrders);
//
//    // Getting list of all orders with a limit of 10
//    List<Order> allOrders = client.getAllOrders(new AllOrdersRequest("LINKETH").limit(10));
//    System.out.println(allOrders);
//
//    // Get status of a particular order
//    Order order = client.getOrderStatus(new OrderStatusRequest("LINKETH", 751698L));
//    System.out.println(order);
//
//    // Canceling an order
//    try {
//      CancelOrderResponse cancelOrderResponse = client.cancelOrder(new CancelOrderRequest("LINKETH", 756762l));
//      System.out.println(cancelOrderResponse);
//    } catch (BinanceApiException e) {
//      System.out.println(e.getError().getMsg());
//    }
//
//    // Placing a test LIMIT order
//    client.newOrderTest(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"));
//
//    // Placing a test MARKET order
//    client.newOrderTest(marketBuy("LINKETH", "1000"));
//
//    // Placing a real LIMIT order
//    NewOrderResponse newOrderResponse = client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001").newOrderRespType(NewOrderResponseType.FULL));
//    System.out.println(newOrderResponse);
  }

}
