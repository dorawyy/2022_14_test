package com.cpen321.flightfriend.ui.filter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cpen321.flightfriend.HelperUtil;
import com.cpen321.flightfriend.R;
import com.cpen321.flightfriend.databinding.FragmentFilterBinding;
import com.cpen321.flightfriend.searchedflights.SearchedFlightsActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class FilterFragment extends Fragment {

    private FragmentFilterBinding binding;
    private Spinner spinnerTier;
    private Spinner spinnerTrip;
    private Button buttonCalendarTwo;
    private TextView tvDateOne;
    private TextView tvDateTwo;
    private AutoCompleteTextView tvLocationOne;
    private AutoCompleteTextView tvLocationTwo;
    private CheckBox cbFlexibility;
    private final String TAG = "FilterFragment";

    public void setSpinners(View root) {
        // Spinner for Class of Travel
        spinnerTier = root.findViewById(R.id.filterTier);
        String[] itemsTier = getResources().getStringArray(R.array.items_tier);
        ArrayAdapter<String> adapterTier =
                new ArrayAdapter<>(
                        this.getContext(), android.R.layout.simple_spinner_item, itemsTier);
        adapterTier.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTier.setAdapter(adapterTier);

        // Spinner for trip direction
        spinnerTrip = root.findViewById(R.id.filterTrip);
        String[] itemsTrip = getResources().getStringArray(R.array.items_trip);
        ArrayAdapter<String> adapterTrip =
                new ArrayAdapter<>(
                        this.getContext(), android.R.layout.simple_spinner_item, itemsTrip);
        adapterTrip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrip.setAdapter(adapterTrip);

        // Spinner for Filter 1
        Spinner spinnerOne = root.findViewById(R.id.filterOne);
        String[] itemsOne = getResources().getStringArray(R.array.items_one);
        ArrayAdapter<String> adapterOne =
                new ArrayAdapter<>(
                        this.getContext(), android.R.layout.simple_spinner_item, itemsOne);
        adapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOne.setAdapter(adapterOne);

        // Spinner for Filter 2
        Spinner spinnerTwo = root.findViewById(R.id.filterTwo);
        String[] itemsTwo = getResources().getStringArray(R.array.items_two);
        ArrayAdapter<String> adapterTwo =
                new ArrayAdapter<>(
                        this.getContext(), android.R.layout.simple_spinner_item, itemsTwo);
        adapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTwo.setAdapter(adapterTwo);

        // Spinner for Filter 3
        Spinner spinnerThree = root.findViewById(R.id.filterThree);
        String[] itemsThree = getResources().getStringArray(R.array.items_three);
        ArrayAdapter<String> adapterThree =
                new ArrayAdapter<>(
                        this.getContext(), android.R.layout.simple_spinner_item, itemsThree);
        adapterThree.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThree.setAdapter(adapterThree);

        // Spinner for Filter 4
        Spinner spinnerFour = root.findViewById(R.id.filterFour);
        String[] itemsFour = getResources().getStringArray(R.array.items_four);
        ArrayAdapter<String> adapterFour =
                new ArrayAdapter<>(
                        this.getContext(), android.R.layout.simple_spinner_item, itemsFour);
        adapterFour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFour.setAdapter(adapterFour);
    }

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFilterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "Jan");
        map.put(1, "Feb");
        map.put(2, "Mar");
        map.put(3, "Apr");
        map.put(4, "May");
        map.put(5, "Jun");
        map.put(6, "Jul");
        map.put(7, "Aug");
        map.put(8, "Sep");
        map.put(9, "Oct");
        map.put(10, "Nov");
        map.put(11, "Dec");

        HashMap<String, Integer> mapInversed = new HashMap<>();
        for (Integer i : map.keySet()) {
            mapInversed.put(map.get(i), i);
        }

        setSpinners(root);

        // Flexibility Checkbox
        cbFlexibility = root.findViewById(R.id.filterFlexibility);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        String dateToday =
                map.get(calendar.get(Calendar.MONTH))
                        + " "
                        + calendar.get(Calendar.DAY_OF_MONTH)
                        + " "
                        + calendar.get(Calendar.YEAR);

        Button buttonCalendarOne = root.findViewById(R.id.calendarButton);
        buttonCalendarTwo = root.findViewById(R.id.calendarButton2);

        tvDateOne = root.findViewById(R.id.dateOne);
        tvDateTwo = root.findViewById(R.id.dateTwo);

        spinnerTrip.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        if (spinnerTrip.getSelectedItemPosition() == 1) {
                            buttonCalendarTwo.setVisibility(View.INVISIBLE);
                            tvDateTwo.setVisibility(View.INVISIBLE);
                        } else {
                            buttonCalendarTwo.setVisibility(View.VISIBLE);
                            tvDateTwo.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // needed for AdapterView
                    }
                });

        tvDateOne.setText(dateToday);
        tvDateTwo.setText(dateToday);

        final int[] dateOne = new int[3];
        dateOne[0] = year;
        dateOne[1] = month;
        dateOne[2] = day;
        final int[] dateTwo = new int[3];
        dateTwo[0] = year;
        dateTwo[1] = month;
        dateTwo[2] = day;

        buttonCalendarOne.setOnClickListener(
                v -> {
                    DatePickerDialog datePickerDialog =
                            new DatePickerDialog(
                                    getContext(),
                                    (view, year1, month1, dayOfMonth) -> {
                                        dateOne[0] = year1;
                                        dateOne[1] = month1;
                                        dateOne[2] = dayOfMonth;
                                        String date =
                                                map.get(month1) + " " + dayOfMonth + " " + year1;
                                        tvDateOne.setText(date);
                                    },
                                    year,
                                    month,
                                    day);
                    datePickerDialog.show();
                });

        buttonCalendarTwo.setOnClickListener(
                v -> {
                    DatePickerDialog datePickerDialog =
                            new DatePickerDialog(
                                    getContext(),
                                    (view, year1, month1, dayOfMonth) -> {
                                        dateTwo[0] = year1;
                                        dateTwo[1] = month1;
                                        dateTwo[2] = dayOfMonth;
                                        String date =
                                                map.get(month1) + " " + dayOfMonth + " " + year1;
                                        tvDateTwo.setText(date);
                                    },
                                    year,
                                    month,
                                    day);
                    datePickerDialog.show();
                });

        tvDateOne.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // needed for TextWatcher
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Snackbar snackbar =
                                Snackbar.make(
                                        root,
                                        "Please select a valid first date",
                                        Snackbar.LENGTH_SHORT);
                        if (dateTwo[0] < dateOne[0] || dateOne[0] < year) {
                            snackbar.show();
                        } else if (dateTwo[0] == dateOne[0] && dateTwo[1] < dateOne[1]) {
                            snackbar.show();
                        } else if (dateOne[0] == year && dateOne[1] < month) {
                            snackbar.show();
                        } else if (dateOne[0] == year && dateOne[1] == month && dateOne[2] < day) {
                            snackbar.show();
                        } else if (dateTwo[0] == dateOne[0]
                                && dateTwo[1] == dateOne[1]
                                && dateTwo[2] < dateOne[2]) {
                            snackbar.show();
                        }
                        Log.d(TAG, "date one is " + Arrays.toString(dateOne));
                        Log.d(TAG, "date two is " + Arrays.toString(dateTwo));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // needed for TextWatcher
                    }
                });

        tvDateTwo.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // needed for TextWatcher
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Snackbar snackbar =
                                Snackbar.make(
                                        root,
                                        "Please select a valid second date",
                                        Snackbar.LENGTH_SHORT);
                        if (dateTwo[0] < dateOne[0]) {
                            snackbar.show();
                        } else if (dateTwo[0] == dateOne[0] && dateTwo[1] < dateOne[1]) {
                            snackbar.show();
                        } else if (dateTwo[0] == dateOne[0]
                                && dateTwo[1] == dateOne[1]
                                && dateTwo[2] < dateOne[2]) {
                            snackbar.show();
                        }
                        Log.d(TAG, "date one is " + Arrays.toString(dateOne));
                        Log.d(TAG, "date two is " + Arrays.toString(dateTwo));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // needed for TextWatcher
                    }
                });

        // Auto dropdown for Origin
        tvLocationOne = root.findViewById(R.id.locationOne);
        // Auto dropdown for Destination
        tvLocationTwo = root.findViewById(R.id.locationTwo);

        Button buttonDestinations = root.findViewById(R.id.destinationsButton);
        buttonDestinations.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavigationView navigationView =
                                requireActivity().findViewById(R.id.nav_view);
                        navigationView.getMenu().performIdentifierAction(R.id.nav_destinations, 0);
                    }
                });

        // Search Button
        Button buttonSearch = root.findViewById(R.id.searchButton);
        buttonSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "search button successfully clicked");
                        Log.d(
                                TAG,
                                "search.onClick: Direction: "
                                        + spinnerTrip.getSelectedItemPosition());
                        Log.d(
                                TAG,
                                "search.onClick: Class: " + spinnerTier.getSelectedItemPosition());
                        Log.d(TAG, "search.onClick: Date1 " + tvDateOne.getText());
                        Log.d(TAG, "search.onClick: Date2 " + tvDateTwo.getText());
                        Log.d(TAG, "search.onClick: flex" + cbFlexibility.isChecked());

                        Bundle bundle = new Bundle();
                        bundle.putInt("direction", spinnerTrip.getSelectedItemPosition());
                        bundle.putInt("tier", spinnerTier.getSelectedItemPosition());
                        bundle.putString("origin", tvLocationOne.getText().toString());
                        bundle.putString("destination", tvLocationTwo.getText().toString());
                        bundle.putString(
                                "dateDepart",
                                HelperUtil.dateFormatter(
                                        tvDateOne.getText().toString(), mapInversed));
                        bundle.putString(
                                "dateReturn",
                                HelperUtil.dateFormatter(
                                        tvDateTwo.getText().toString(), mapInversed));
                        bundle.putBoolean("flex", cbFlexibility.isChecked());

                        openSearchedFlightsActivity(bundle);
                    }
                });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set both adapter to the flight db list
        String[] airportLocationNames =
                HelperUtil.getLargeAirportNames(requireActivity()).values().toArray(new String[0]);

        ArrayAdapter<String> adapterLocationOne =
                new ArrayAdapter<>(
                        getContext(), android.R.layout.simple_list_item_1, airportLocationNames);
        tvLocationOne.setAdapter(adapterLocationOne);

        ArrayAdapter<String> adapterLocationTwo =
                new ArrayAdapter<>(
                        getContext(), android.R.layout.simple_list_item_1, airportLocationNames);
        tvLocationTwo.setAdapter(adapterLocationTwo);
    }

    private void openSearchedFlightsActivity(Bundle bundle) {
        Intent intent = new Intent(getContext(), SearchedFlightsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
