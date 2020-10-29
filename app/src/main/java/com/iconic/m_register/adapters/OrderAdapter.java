package com.iconic.m_register.adapters;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iconic.m_register.R;
import com.iconic.services.models.Order;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>  {

    private List<Order> orders;
    Context context;

    public OrderAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_item,parent,false);
        OrderViewHolder viewHolder = new OrderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind_Orders(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.this_number) TextView mOrderNumber;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.this_destination) TextView mOrderDestination;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.this_card_owner) TextView mOrderHolder;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bind_Orders(Order order){
            mOrderNumber.setText(order.getNumberOfCards());
            mOrderDestination.setText(order.getDestination());
            mOrderHolder.setText(order.getOrderedBy());
        }
    }
}
