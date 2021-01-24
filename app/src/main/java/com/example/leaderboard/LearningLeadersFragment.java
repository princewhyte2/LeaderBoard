package com.example.leaderboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearningLeadersFragment extends Fragment {
    //

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mTextview;
    private ArrayList<Learners> mLearnersData;
    private LearnersAdapter mAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.learning_leaders, container, false);

        // Initialize the RecyclerView.
        mProgressBar = view.findViewById(R.id.progress_bar);
        mTextview = view.findViewById(R.id.check_network);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setVisibility(View.GONE);
        mTextview.setVisibility(View.GONE);


        // Initialize the ArrayList that will contain the data.
        mLearnersData = new ArrayList<>();
        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new LearnersAdapter(getContext(),mLearnersData);
        mRecyclerView.setAdapter(mAdapter);

        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            mTextview.setVisibility(View.GONE);
            initializeData();

            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            // display error
            mRecyclerView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
            mTextview.setVisibility(View.VISIBLE);

        }
        return view;
    }

    private void initializeData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL) // Specify your api here
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<List<Learners>> call = api.getLearners();
        call.enqueue(new Callback<List<Learners>>() {
            @Override
            public void onResponse(Call<List<Learners>> call, Response<List<Learners>> response) {
                List<Learners> learners = response.body();
                for ( Learners l : learners) {
                  mLearnersData.add(new Learners(l.getName(),l.getHours(),l.getCountry(),l.getImageResource()));
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Learners>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}