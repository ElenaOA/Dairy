package com.example.diary.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.diary.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    private MaterialCalendarView materialCalendarView;
    private Calendar calendar;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        materialCalendarView = view.findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();
        materialCalendarView.setSelectedDate(calendar);
        Button buttonYear = view.findViewById(R.id.button_year);
        Button buttonYearMonth = view.findViewById(R.id.button_year_month);
        Button buttonMonth = view.findViewById(R.id.button_month);

        buttonYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("AddRecipe")
                        .replace(R.id.fragment_content, new PlansOnYearFragment())
                        .commit();
            }
        });

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget,
                                       @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(getContext(), "" + calendar.toString(), Toast.LENGTH_SHORT).show();
                calendar.set(date.getYear(), date.getMonth(), date.getDay());
            }
        });

        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                materialCalendarView.setSelectedDate(date);
                calendar.set(date.getYear(), date.getMonth(), date.getDay());
            }
        });

        final MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(view.getContext(),
                new MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) { // on date set }
                    }}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

        builder.setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(int selectedMonth) {
                Toast.makeText(getContext(), "Month: " + selectedMonth, Toast.LENGTH_SHORT).show();
                calendar.set(Calendar.MONTH, selectedMonth);
                materialCalendarView.setSelectedDate(calendar);
                materialCalendarView.setCurrentDate(calendar);
            }
        });

        builder.setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
            @Override
            public void onYearChanged(int year) {
                Toast.makeText(getContext(), "Year: " + year, Toast.LENGTH_SHORT).show();
                calendar.set(Calendar.YEAR, year);
                materialCalendarView.setSelectedDate(calendar);
                materialCalendarView.setCurrentDate(calendar);
            }
        });



        buttonYearMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "" + calendar.get(Calendar.MONTH) + ' ' +
                        calendar.get(Calendar.YEAR), Toast.LENGTH_SHORT).show();
                builder.setActivatedMonth(calendar.get(Calendar.MONTH))
                        .setMinYear(2000)
                        .setActivatedYear(calendar.get(Calendar.YEAR))
                        .setMaxYear(2030)
                        //.setMinMonth(Calendar.FEBRUARY)
                        .setTitle("Выберите месяц и год")
                        //.setMonthRange(Calendar.FEBRUARY, Calendar.NOVEMBER)
                        // .setMaxMonth(Calendar.OCTOBER)
                        // .setYearRange(1890, 1890)
                        // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
                        // .showMonthOnly()
                        // .showYearOnly()
                        .build().show();
            }
        });
        return view;
    }



}
