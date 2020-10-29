package com.iconic.m_register.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.iconic.m_register.R;
import com.iconic.m_register.adapters.SearchAdapter;
import com.iconic.services.RegisterClient;
import com.iconic.services.RegisterService;
import com.iconic.services.models.Member;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.search_text) SearchView mSearchView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.search_results) RecyclerView mSearchResults;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress) ProgressBar mProgressBar;
    private List<Member> memberList;


    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final String search_text = mSearchView.getQuery().toString();
                if(search_text != null || !search_text.equals("")){
                    RegisterService client =  RegisterClient.get_user();
                    Call<List<Member>> call = client.search_member(search_text);
                    call.enqueue(new Callback<List<Member>>() {

                        @Override
                        public void onResponse(@NotNull Call<List<Member>> call, @NotNull Response<List<Member>> response) {
                            display_progress();
                            if(response.isSuccessful()){
                                memberList = response.body();
                                SearchAdapter adapter = new SearchAdapter(memberList,getContext());
                                mSearchResults.setAdapter(adapter);
                                mSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
                                display_results();
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<List<Member>> call, @NotNull Throwable t) {

                        }
                    });
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;
    }
    public void display_progress(){
        mProgressBar.setVisibility(View.VISIBLE);
    }
    public void display_results(){
        mSearchResults.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }
}