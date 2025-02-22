package com.binance.api.examples;

import com.osa.binance.api.client.BinanceApiClientFactory;
import com.osa.binance.api.client.BinanceApiSwapRestClient;
import com.osa.binance.api.client.domain.account.Liquidity;
import com.osa.binance.api.client.domain.account.Pool;
import com.osa.binance.api.client.domain.account.SwapHistory;
import com.osa.binance.api.client.domain.account.SwapQuote;
import com.osa.binance.api.client.domain.account.SwapRecord;

import java.util.List;

public class SwapEndpointExample {

    public static String API_KEY = "api-key";
    public static String SECRET_KEY = "secret-key";

    public static void main(String[] args) {

        BinanceApiClientFactory binanceApiClientFactory = BinanceApiClientFactory.getInstance(API_KEY, SECRET_KEY, false);
        BinanceApiSwapRestClient swapClient = binanceApiClientFactory.newSwapRestClient();
        List<Pool> pools = swapClient.listAllSwapPools();
        for(Pool pool:pools) {
            System.out.println(pool);
            Liquidity poolLiquidityInfo = swapClient.getPoolLiquidityInfo(pool.getPoolId());
            System.out.println(poolLiquidityInfo);
        }
        SwapQuote swapQuote = swapClient.requestQuote("USDT", "USDC", "10");
        System.out.println(swapQuote);
        SwapRecord swapRecord = swapClient.swap("USDT", "USDC", "10");
        SwapHistory swapHistory = swapClient.getSwapHistory(swapRecord.getSwapId());
        System.out.println(swapHistory);
    }


}
