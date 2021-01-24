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

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<SkillClass> mLearnersData;
    private Context mContext;

    SkillAdapter(Context context, ArrayList<SkillClass> learnersData) {
        this.mLearnersData = learnersData;
        this.mContext = context;

    }

    @NonNull
    @Override
    public SkillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SkillAdapter.ViewHolder holder, int position) {
        // Get current sport.
        SkillClass currentLearner = mLearnersData.get(position);

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
            mLeaderName = itemView.findViewById(R.id.leader_name2);
            mLeaderLocation = itemView.findViewById(R.id.leader_location2);
            mLeaderBadge = itemView.findViewById(R.id.leader_badge2);
        }

        void bindTo(SkillClass currentLearner){
            // Populate the textviews with data.
            mLeaderName.setText(currentLearner.getName());
            mLeaderLocation.setText(currentLearner.getScore() + " skill IQ Score, " + currentLearner.getCountry());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentLearner.getImageResource()).into(mLeaderBadge);
        }
    }
}
