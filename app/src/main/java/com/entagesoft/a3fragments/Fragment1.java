package com.entagesoft.a3fragments;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment1 extends Fragment {

    EditText nameEditText, surnameEditText, emailEditText, telephoneEditText;
    Button saveButton;
    DBHelper DBHelper;
    ContentValues contentValues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        saveButton = (Button) view.findViewById(R.id.save);
        nameEditText = (EditText) view.findViewById(R.id.name);
        surnameEditText = (EditText) view.findViewById(R.id.surname);
        emailEditText = (EditText) view.findViewById(R.id.email);
        telephoneEditText = (EditText) view.findViewById(R.id.telephone);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString();
                String surname = surnameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String telephone = telephoneEditText.getText().toString();

                DBHelper = new DBHelper(getActivity());
                DBHelper.open();

                if(DBHelper.addItem(name, surname, email, telephone)) {

                    Toast.makeText(getActivity(), "Successfully saved", Toast.LENGTH_LONG).show();
                }
                else{

                    Toast.makeText(getActivity(), "Not saved", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }
}
