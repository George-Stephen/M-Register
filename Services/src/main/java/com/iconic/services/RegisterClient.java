package com.iconic.services;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterClient {
    private static Retrofit retrofit ;
    public static RegisterService get_user(){
        if(retrofit==null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .build();
                            return  chain.proceed(request);
                        }
                    }).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.base_url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit.create(RegisterService.class);
    }
}
