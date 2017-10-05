package com.entagesoft.a3fragments;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
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
    SharedPreferences sharedPreferences;

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

                sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                Editor editor = sharedPreferences.edit();
                editor.putString("name", name);
                editor.putString("surname", surname);
                editor.putString("email", email);
                editor.putString("telephone", telephone);
                editor.commit();

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

                    Toast.makeText(getActivity(), R.string.toast_success, Toast.LENGTH_LONG).show();
                }
                else{

                    Toast.makeText(getActivity(), R.string.toast_error, Toast.LENGTH_LONG).show();
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
