package com.entagesoft.a3fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 3 onAttach"));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 3 onCreate"));
    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 3 onStart"));
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 3 onResume"));
    }

    @Override
    public void onPause() {
        super.onPause();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 3 onPause"));
    }

    @Override
    public void onStop() {
        super.onStop();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 3 onStop"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 3 onDestroyView"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 3 onDestroy"));
    }

    @Override
    public void onDetach() {
        super.onDetach();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 3 onDetach"));
    }
}
