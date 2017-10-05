package com.entagesoft.a3fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Fragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, container, false);

        TextView nameText = (TextView) view.findViewById(R.id.textView);
        TextView surText = (TextView) view.findViewById(R.id.textView2);
        TextView emailText = (TextView) view.findViewById(R.id.textView3);
        TextView telText = (TextView) view.findViewById(R.id.textView4);

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);

        nameText.setText(sp.getString("name", null));
        surText.setText(sp.getString("surname", null));
        emailText.setText(sp.getString("email", null));
        telText.setText(sp.getString("telephone", null));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d("MY_TAG", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("MY_TAG", "onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d("MY_TAG", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("MY_TAG", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d("MY_TAG", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d("MY_TAG", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("MY_TAG", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("MY_TAG", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d("MY_TAG", "onDetach");
    }
}
