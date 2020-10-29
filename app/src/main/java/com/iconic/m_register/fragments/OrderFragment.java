package com.iconic.m_register.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iconic.m_register.R;
import com.iconic.services.RegisterClient;
import com.iconic.services.RegisterService;
import com.iconic.services.models.Order;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment implements View.OnClickListener {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.number_cards) EditText mNumberCards;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.destination) EditText mDestination;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.card_holder) EditText mCardHolder;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.order_submit) Button mOrderButton;





    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this,view);
        mOrderButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == mOrderButton){
            String number = mNumberCards.getText().toString();
            String destination = mDestination.getText().toString();
            String card_holder = mCardHolder.getText().toString();

           //validation
           if (number.equals("")){
               mNumberCards.setError("Please enter the number of cards");
           }
           else  if (destination.equals("")){
               mDestination.setError("Please enter the destination");
           }
           else if (card_holder.equals("")){
               mCardHolder.setError("Please enter the card holder's name");
           }
           else {
               RegisterService service = RegisterClient.get_user();
               Call<Order> call = service.add_order(number,destination,card_holder);
               call.enqueue(new Callback<Order>() {
                   @Override
                   public void onResponse(@NotNull Call<Order> call, @NotNull Response<Order> response) {
                       if (response.isSuccessful()){
                           Toast.makeText(getContext(),"Order successfully added",Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(@NotNull Call<Order> call, @NotNull Throwable t) {
                       Toast.makeText(getContext(),"Order not successful, try again later",Toast.LENGTH_SHORT).show();
                   }
               });
           }
        }

    }
}