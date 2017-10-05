package com.entagesoft.a3fragments;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN;

public class Fragment1 extends Fragment {

    EditText nameEditText, surnameEditText, emailEditText, telephoneEditText;
    Button saveButton;
    DBHelper DBHelper;
    Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        saveButton = (Button) view.findViewById(R.id.save);
        nameEditText = (EditText) view.findViewById(R.id.name);
        surnameEditText = (EditText) view.findViewById(R.id.surname);
        emailEditText = (EditText) view.findViewById(R.id.email);
        telephoneEditText = (EditText) view.findViewById(R.id.telephone);

        if(MainActivity.EDIT_REC_ID != 0) {

            DBHelper = new DBHelper(getActivity());
            DBHelper.open();
            cursor = DBHelper.getOneRec(MainActivity.EDIT_REC_ID);

            cursor.moveToFirst();
            if(cursor != null) {

                nameEditText.setText(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME)));
                surnameEditText.setText(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_SURNAME)));
                emailEditText.setText(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_EMAIL)));
                telephoneEditText.setText(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TELEPHONE)));
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString();
                String surname = surnameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String telephone = telephoneEditText.getText().toString();

                boolean succ = false;
                DBHelper = new DBHelper(getActivity());
                DBHelper.open();

                if(MainActivity.EDIT_REC_ID != 0) {

                    succ = DBHelper.updateItem(MainActivity.EDIT_REC_ID, name, surname, email, telephone);
                }

                else{

                    succ = DBHelper.addItem(name, surname, email, telephone);
                }
                if(succ) {

                    Toast.makeText(getActivity(), "Successfully saved", Toast.LENGTH_LONG).show();
                }
                else{

                    Toast.makeText(getActivity(), "Not saved", Toast.LENGTH_LONG).show();
                }

                MainActivity.EDIT_REC_ID = 0;
//переход на 2 фрагменрт
                android.app.FragmentTransaction fragmentTransaction;
                Fragment fragment_2 = new Fragment2();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment_2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
