package com.example.leaderboard;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillIQLeadersFragment extends Fragment {
    private static final String JSON_URL = "https://gadsapi.herokuapp.com/api/skillq";
    // Member variables.
    private ProgressBar mProgressBar;
    private TextView mTextview;
    private RecyclerView mRecyclerView;
    private ArrayList<SkillClass> mLearnersData;
    private SkillAdapter mAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.skilliq_leaders, container, false);
        view.findViewById(R.id.check_network2).setVisibility(View.GONE);


        // Initialize the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recyclerview2);

        mTextview = view.findViewById(R.id.check_network2);
        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setVisibility(View.GONE);


        // Initialize the ArrayList that will contain the data.
        mLearnersData = new ArrayList<>();
        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new SkillAdapter(getContext(), mLearnersData);
        mRecyclerView.setAdapter(mAdapter);

        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            mTextview.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            initializeData();


        } else {
            // display error
            mRecyclerView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
            mTextview.setVisibility(View.VISIBLE);

        }
        return view;
    }

    private void initializeData() {
        //getting the progressbar


        //Retrofit retrofit = new Retrofit.Builder()
        //     .baseUrl(Apib.BASE_URL) // Specify your api here
        //   .addConverterFactory(GsonConverterFactory.create())
        // .build();
        //Api api =  retrofit.create(Api.class);
        //Call<List<SkillClass>> call = api.g;
        //call.enqueue(new Callback<List<SkillClass>>() {
        //  @Override
        // public void onResponse(Call<List<SkillClass>> call, Response<List<SkillClass>> response) {
        //   List<SkillClass> learners = response.body();
        // for ( SkillClass l : learners) {
        //     mLearnersData.add(new SkillClass(l.getName(),l.getScore(),l.getCountry(),l.getImageResource()));
        //}
        //mAdapter.notifyDataSetChanged();
        // }
        //@Override
        //public void onFailure(Call<List<SkillClass>> call, Throwable t) {
        //   Toast.makeText(getContext(), ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        //}
        //});
        String skillIQLeadersUrl = "https://gadsapi.herokuapp.com/api/skilliq";
        JsonArrayRequest request = new JsonArrayRequest(skillIQLeadersUrl, response -> {
            JSONObject j;
            for (int i = 0; i < response.length(); i++) {

                try {

                    j = response.getJSONObject(i);
                    mLearnersData.add(new SkillClass(j.getString("name"),j.getInt("score"),j.getString("country"),j.getString("badgeUrl")));
                   // SkillIQModel model = new SkillIQModel();
                    //model.setName(jsonObject.getString("name"));
                    //model.setCountry(jsonObject.getString("country"));
                    //model.setScore(jsonObject.getInt("score"));
                    //model.setBadgeUrl(jsonObject.getString("badgeUrl"));
                    //skillIQModelList.add(model);

                    //setupRecyclerView(skillIQModelList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}