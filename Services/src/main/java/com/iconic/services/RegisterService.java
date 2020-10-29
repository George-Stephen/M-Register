package com.iconic.services;

import com.iconic.services.models.Member;
import com.iconic.services.models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterService {

    @POST("api/member/new")
    @FormUrlEncoded
    Call<Member> add_members(@Field("id_number") String id_number,@Field("full_name") String full_name,@Field("gender") String gender,@Field("date_birth") String date_birth,@Field("phone_number") String phone_number,@Field("county") String county,@Field("sub_county") String sub_county);

    @GET("api/member/search/")
    Call<List<Member>> search_member(@Query("search") String search);

    @POST("api/order/new/")
    @FormUrlEncoded
    Call<Order> add_order(@Field("number_of_cards") String number_cards,@Field("destination") String destination,@Field("ordered_by") String ordered_by);

    @GET("api/orders/")
    Call<List<Order>> get_orders();
}
