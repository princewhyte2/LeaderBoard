package com.example.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class LearnersAdapter extends RecyclerView.Adapter<LearnersAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Learners> mLearnersData;
    private Context mContext;

    LearnersAdapter(Context context, ArrayList<Learners> learnersData) {
        this.mLearnersData = learnersData;
        this.mContext = context;

    }

    @NonNull
    @Override
    public LearnersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LearnersAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Learners currentLearner = mLearnersData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentLearner);
    }

    @Override
    public int getItemCount() {
        return mLearnersData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mLeaderName;
        private TextView mLeaderLocation;
        private ImageView mLeaderBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views.
            mLeaderName = itemView.findViewById(R.id.leader_name);
            mLeaderLocation = itemView.findViewById(R.id.leader_location);
            mLeaderBadge = itemView.findViewById(R.id.leader_badge);
        }

        void bindTo(Learners currentLearner){
            // Populate the textviews with data.
            mLeaderName.setText(currentLearner.getName());
            mLeaderLocation.setText(currentLearner.getHours() + " learning hours, " + currentLearner.getCountry());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentLearner.getImageResource()).into(mLeaderBadge);
        }
    }
}
