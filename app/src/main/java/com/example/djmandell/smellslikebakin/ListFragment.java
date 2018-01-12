package com.example.djmandell.smellslikebakin;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by djmandell on 12/17/16.
 */
public class ListFragment extends Fragment {
    public interface OnRecipeSelectedInterface{
        void onListRecipeSeleted(int index);
    }

    @Nullable
    @Override
    // inflaters turn xml layouts into views
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // third parameter determines whether this view is attached to the ViewGroup in second parameter.
        // Apparently onCreateView does this once automatically, so we don't want to do it twice
        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);
        ListAdapter listAdapter = new ListAdapter(listener);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        return view;

    }
}
