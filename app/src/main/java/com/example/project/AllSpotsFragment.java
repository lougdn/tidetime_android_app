package com.example.project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class AllSpotsFragment extends Fragment {

    public AllSpotsFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_all_spots, container, false);
        String[] allspots = {"Spot 1", "Spot 2"};

        ListView listView = (ListView) v.findViewById(R.id.spotslist);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                allspots
        );

        listView.setAdapter(listViewAdapter);

        ViewGroup CreateButton = (ViewGroup) v.findViewById(R.id.spotslist);


        return v;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        getFragmentManager().beginTransaction()
                .replace(((ViewGroup) getView().getParent()).getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
}


