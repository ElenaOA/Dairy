package com.example.diary.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diary.R;
import com.example.diary.entity.Plan;

import java.util.ArrayList;
import java.util.List;

public class PlanOnYearAdapter extends RecyclerView.Adapter<PlanOnYearAdapter.ViewHolder> {

    List<Plan> plans = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plan_note, parent, false);
        return new ViewHolder(view);
    }

    public void setItems(List<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(plans.get(i));
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView planName;

        public ViewHolder(View view) {
            super(view);

            planName = view.findViewById(R.id.text_plan_name);
        }

        public void bind(Plan plan) {
            planName.setText(plan.getName());
        }
    }
}
