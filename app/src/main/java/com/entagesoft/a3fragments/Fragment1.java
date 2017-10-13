package com.entagesoft.a3fragments;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 1 onAttach"));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 1 onCreate"));
    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 1 onStart"));
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 1 onResume"));
    }

    @Override
    public void onPause() {
        super.onPause();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 1 onPause"));
    }

    @Override
    public void onStop() {
        super.onStop();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 1 onStop"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 1 onDestroyView"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 1 onDestroy"));
    }

    @Override
    public void onDetach() {
        super.onDetach();

        getActivity().startService(new Intent(getActivity(), LogService.class).putExtra("string", " Fragment 1 onDetach"));
    }
}
