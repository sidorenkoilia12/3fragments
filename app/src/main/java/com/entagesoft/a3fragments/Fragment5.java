package com.entagesoft.a3fragments;


import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment5 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);
        //actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Settings");
        actionBar.setDisplayHomeAsUpEnabled(true);


        return inflater.inflate(R.layout.fragment_5, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                getActivity().onBackPressed();
                Toast.makeText(getActivity(), "Back", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
