package com.entagesoft.a3fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Fragment2 extends Fragment {

    ListView listView;
    DBHelper DBHelper;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, container, false);

        listView = (ListView) view.findViewById(R.id.listView);

        DBHelper = new DBHelper(getActivity());
        DBHelper.open();

        cursor = DBHelper.getAllData();
        String[] from = new String[] {DBHelper.KEY_NAME, DBHelper.KEY_SURNAME, DBHelper.KEY_EMAIL, DBHelper.KEY_TELEPHONE};
        int[] to = new int[] {R.id.item_name, R.id.item_surname, R.id.item_email, R.id.item_telephone};

        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.item, cursor, from, to);
        listView.setAdapter(simpleCursorAdapter);

        return view;
    }

}
