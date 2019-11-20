package com.example.diary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diary.R;
import com.example.diary.adapter.PlanOnYearAdapter;
import com.example.diary.entity.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlansOnYearFragment extends Fragment {

    private RecyclerView recyclerView;

    public PlansOnYearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plans_on_year, container, false);

        initRecyclerView(view);

        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_plan_year);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        PlanOnYearAdapter adapter = new PlanOnYearAdapter();
        recyclerView.setAdapter(adapter);
        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan("жопа ты"));
        plans.add(new Plan("жопу помыть"));
        plans.add(new Plan("жопа ты2"));
        adapter.setItems(plans);
    }

}
