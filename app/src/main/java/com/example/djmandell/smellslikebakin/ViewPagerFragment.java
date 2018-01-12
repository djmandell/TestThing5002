package com.example.djmandell.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * The ViewPager is what's going to let me swipe left/right between
 * different fragments containing different dates.
 * Also should let me use a date picker (?)
 */
public class ViewPagerFragment extends Fragment {

    public static final String KEY_RECIPE_INDEX = "recipe_index";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(KEY_RECIPE_INDEX);


        // SETS TITLE TO THE APP NAME PLUS RECIPE NAME (APP NAME PLUS DATE FOR ME)
        getActivity().setTitle(getString(R.string.app_name) + "  -  " + Recipes.names[index]);
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        final IngredientsFragment ingredientsFragment = new IngredientsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX,index);
        ingredientsFragment.setArguments(bundle);
        final DirectionsFragment directionsFragment = new DirectionsFragment();
        directionsFragment.setArguments(bundle);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        // ChildFragmentManager is for fragments within fragments.  Shouldn't be necessary for me
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0){
                    return ingredientsFragment;
                }
                return directionsFragment;

            }

            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "Ingredients" : "Directions";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        //TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        //tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
