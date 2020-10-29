package com.iconic.m_register.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iconic.m_register.R;
import com.iconic.services.models.Member;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<Member> memberList;
    Context context;

    public SearchAdapter(List<Member> memberList, Context context) {
        this.memberList = memberList;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.member_item,parent,false);
        SearchViewHolder viewHolder = new SearchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Member member = memberList.get(position);
        holder.bind_member(member);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder{
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.member_name) TextView mMemberView;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.member_id) TextView mMemberId;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.member_county) TextView mMemberCounty;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.member_subcounty) TextView mMemberSub_county;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bind_member(Member member){
            mMemberView.setText(member.getFullName());
            mMemberId.setText(member.getIdNumber());
            mMemberCounty.setText(member.getCounty());
            mMemberSub_county.setText(member.getSubCounty());

        }
    }
}
