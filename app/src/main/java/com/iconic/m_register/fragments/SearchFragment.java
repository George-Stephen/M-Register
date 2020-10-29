package com.iconic.m_register.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.ToneGenerator;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.iconic.m_register.R;
import com.iconic.m_register.adapters.SearchAdapter;
import com.iconic.services.RegisterClient;
import com.iconic.services.RegisterService;
import com.iconic.services.models.Member;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    private BarcodeDetector mBarcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_IMAGE_CAPTURE = 201;
    private ToneGenerator mTone;
    private String CodeData;
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
                String search_text = mSearchView.getQuery().toString();
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
/*
    public String getText(){
        mBarcodeDetector =  new BarcodeDetector.Builder(getContext()).setBarcodeFormats(Barcode.ALL_FORMATS).build();
        cameraSource = new CameraSource.Builder(Objects.requireNonNull(getContext()),mBarcodeDetector)
                .setRequestedPreviewSize(1920,1080)
                .setAutoFocusEnabled(true)
                .build();
        mScanner.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                   if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        cameraSource.start(mScanner.getHolder());
                   }
                   else{
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},REQUEST_IMAGE_CAPTURE);
                   }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        mBarcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                  final SparseArray<Barcode> barCodes = detections.getDetectedItems();
                  if (barCodes.size() != 0){
                      mText.post(new Runnable() {
                          @Override
                          public void run() {
                                if (barCodes.valueAt(0).email != null){
                                    mText.removeCallbacks(null);
                                    CodeData = barCodes.valueAt(0).email.address;

                                }else {
                                    CodeData = barCodes.valueAt(0).displayValue;


                                }
                              mTone.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                          }
                      });
                  }
            }
        });
        return CodeData;
    }

 */
}