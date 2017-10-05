package com.entagesoft.a3fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN;

public class Fragment2 extends Fragment {

    ListView listView;
    FloatingActionButton floatingActionButton;
    DBHelper DBHelper;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, container, false);

        listView = (ListView) view.findViewById(R.id.listView);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.FragmentTransaction fragmentTransaction;
                Fragment fragment_1 = new Fragment1();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment_1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }
        });

        refreshList();

        registerForContextMenu(listView);

        return view;
    }

    public void refreshList (){

        DBHelper = new DBHelper(getActivity());
        DBHelper.open();

        cursor = DBHelper.getAllData();
        String[] from = new String[] {DBHelper.KEY_NAME, DBHelper.KEY_SURNAME, DBHelper.KEY_EMAIL, DBHelper.KEY_TELEPHONE};
        int[] to = new int[] {R.id.item_name, R.id.item_surname, R.id.item_email, R.id.item_telephone};

        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.item, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 1, 0, "delete");
        menu.add(0, 2, 0, "edit");
        menu.add(0, 3, 0, "mail");
        menu.add(0, 4, 0, "call");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId() == 1) {
            AdapterContextMenuInfo acmi;
            acmi = (AdapterContextMenuInfo) item.getMenuInfo();
            DBHelper.deleteRec(acmi.id);
            refreshList();

            return true;
        }

        if(item.getItemId() == 2){

            AdapterContextMenuInfo acmi;
            acmi = (AdapterContextMenuInfo) item.getMenuInfo();
            MainActivity.EDIT_REC_ID = acmi.id;

            android.app.FragmentTransaction fragmentTransaction;
            Fragment fragment_1 = new Fragment1();
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment_1);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();

            return true;
        }

        if(item.getItemId() == 3){


            return true;
        }

        if(item.getItemId() == 4){


            return true;
        }

        return super.onContextItemSelected(item);
    }
}
