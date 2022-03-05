package com.example.workout_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// main class
public class AdapterForRecycleView extends RecyclerView.Adapter<AdapterForRecycleView.ViewHolder> {

    // properties
    Context context;
    ArrayList<WorkoutsDetails> list;

    // constructor
    public AdapterForRecycleView(Context context, ArrayList<WorkoutsDetails> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // add data to the view holder
        WorkoutsDetails workoutsDetails = list.get(position);
        holder.workoutTitle.setText(workoutsDetails.getTitle());
        holder.typeOfWorkout.setText(workoutsDetails.getTypeofWorkout());
        holder.workoutDetails.setText(workoutsDetails.getWorkout_details());
        holder.numOfSetsOrDuration.setText(workoutsDetails.getSets());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // inner class
    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView workoutTitle, typeOfWorkout, workoutDetails, numOfSetsOrDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // set the data from the ITEM layout
            workoutTitle = itemView.findViewById(R.id.item_workout_title_card);
            typeOfWorkout = itemView.findViewById(R.id.item_type_of_workout_card);
            workoutDetails = itemView.findViewById(R.id.item_workout_details_card);
            numOfSetsOrDuration = itemView.findViewById(R.id.item_duration_or_sets_card);
        }
    }
}
