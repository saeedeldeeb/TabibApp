package com.example.eldeeb.tabibapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class search extends Fragment {

AutoCompleteTextView governAuto,regionAuto,specialityAuto;
private String[] govern,region,speciality;
ArrayAdapter<String> govern_adapter,region_adapter,speciality_adapter;
Button searchbtn;



    public search() {
        // Required empty public constructor
    }


    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_search, container, false);
        autoMethod();
        searchbtn = root.findViewById(R.id.btn_search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),docs_list.class);
                i.putExtra("region",region);
                i.putExtra("speciality_chosen",speciality);
                startActivity(i);
            }
        });
        return root;
    }

    void autoMethod(){
        //Govern complete
        governAuto = root.findViewById(R.id.governAuto);
        govern = getResources().getStringArray(R.array.governerator);
        govern_adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,govern);
        governAuto.setAdapter(govern_adapter);

        //Region complete
        regionAuto = root.findViewById(R.id.regionAuto);
        region = getResources().getStringArray(R.array.cairo_regions);
        region_adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,region);
        regionAuto.setAdapter(region_adapter);

        //Speciality complete
        specialityAuto = root.findViewById(R.id.specialityAuto);
        speciality = getResources().getStringArray(R.array.speciality);
        speciality_adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,speciality);
        specialityAuto.setAdapter(speciality_adapter);

    }





    @Override
    public void onDetach() {
        super.onDetach();
    }


}
