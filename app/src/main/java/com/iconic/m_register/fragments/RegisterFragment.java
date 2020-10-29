package com.iconic.m_register.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iconic.m_register.R;
import com.iconic.services.RegisterClient;
import com.iconic.services.RegisterService;
import com.iconic.services.models.Member;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment implements View.OnClickListener {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.new_id) EditText mNewId;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.new_name) EditText mNewName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.new_phone)EditText mNewPhone;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.new_dob) EditText mNewDate;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.new_gender) EditText mNewGender;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.new_county) EditText mNewCounty;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.new_subcounty) EditText mNewSub_county;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.new_add) Button mSubmitButton;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this,view);
        mSubmitButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == mSubmitButton){
            // variable declaration

            String id = mNewId.getText().toString();
            String full_name = mNewName.getText().toString();
            String gender =mNewGender.getText().toString();
            String date = mNewDate.getText().toString();
            String phone = mNewPhone.getText().toString();
            String county = mNewCounty.getText().toString();
            String sub_county = mNewSub_county.getText().toString();

            //input-validation
            if(id.equals("")){
                mNewId.setError("Please enter the id number");
            }
            else if(full_name.equals("")){
                mNewName.setError("Please enter the phone number");
            }
            else if (gender.equals("")){
                mNewGender.setError("Please enter the email address");
            }
            else if (phone.equals("")|| phone.length() > 10){
                mNewPhone.setError("Please enter the correct phone number");
            }
            else if (county.equals("")){
                mNewCounty.setError("Please enter the county");
            }
            else if (sub_county.equals("")){
                mNewSub_county.setError("Please enter the sub-county");
            }
            else {
                RegisterService client = RegisterClient.get_user();
                Call<Member> memberCall = client.add_members(id,full_name,gender,date,phone,county,sub_county);
                memberCall.enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getActivity(), "Member successfully added", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Member> call, Throwable t) {
                        Toast.makeText(getActivity(), "Cannot enter member right now", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }
    }


}