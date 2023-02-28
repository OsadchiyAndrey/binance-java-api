package com.osa.binance.api.client.impl;

import com.osa.binance.api.client.BinanceApiError;
import com.osa.binance.api.client.config.BinanceConfig;
import com.osa.binance.api.client.exception.BinanceApiException;
import com.osa.binance.api.client.security.AuthenticationInterceptor;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

/**
 * Generates a Binance API implementation based on @see {@link BinanceApiService}.
 */
public class BinanceApiServiceProducer {

    private static final OkHttpClient sharedClient;
    private static final Converter.Factory converterFactory;
    private static final Converter<ResponseBody, BinanceApiError> errorBodyConverter;

    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(500);
        dispatcher.setMaxRequests(500);
        converterFactory = JacksonConverterFactory.create();
        errorBodyConverter = (Converter<ResponseBody, BinanceApiError>) converterFactory.responseBodyConverter(
            BinanceApiError.class, new Annotation[0], null);
        sharedClient = new OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .pingInterval(20, TimeUnit.SECONDS)
            .build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, BinanceConfig binanceConfig) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(binanceConfig.getApiUrl())
            .addConverterFactory(converterFactory);

        AuthenticationInterceptor interceptor
            = new AuthenticationInterceptor(binanceConfig.getAccessKey(), binanceConfig.getSecret());
        OkHttpClient adaptedClient = sharedClient.newBuilder().addInterceptor(interceptor).build();
        retrofitBuilder.client(adaptedClient);

        return retrofitBuilder.build().create(serviceClass);
    }

    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                BinanceApiError apiError = getBinanceApiError(response);
                throw new BinanceApiException(apiError);
            }
        } catch (IOException e) {
            throw new BinanceApiException(e);
        }
    }

    public static BinanceApiError getBinanceApiError(Response<?> response) throws IOException, BinanceApiException {
        return errorBodyConverter.convert(response.errorBody());
    }

    public static OkHttpClient getSharedClient() {
        return sharedClient;
    }
}
