package com.iconic.m_register.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iconic.m_register.R;
import com.iconic.m_register.adapters.OrderAdapter;
import com.iconic.services.RegisterClient;
import com.iconic.services.RegisterService;
import com.iconic.services.models.Order;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DisplayFragment extends Fragment {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.order_view) RecyclerView mOrderView;
    private OrderAdapter adapter;


    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this,view);
        RegisterService service = RegisterClient.get_user();
        Call<List<Order>> call = service.get_orders();
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NotNull Call<List<Order>> call, @NotNull Response<List<Order>> response) {
                if(response.isSuccessful()){
                    List<Order> orderList = response.body();
                    adapter = new OrderAdapter(orderList,getContext());
                    mOrderView.setAdapter(adapter);
                    mOrderView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
        return view;
    }
}