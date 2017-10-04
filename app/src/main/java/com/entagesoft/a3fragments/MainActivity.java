package com.entagesoft.a3fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import static android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN;

public class MainActivity extends AppCompatActivity {

    Fragment fragment_1;
    Fragment fragment_2;
    Fragment fragment_3;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_1 = new Fragment1();
        fragment_2 = new Fragment2();
        fragment_3 = new Fragment3();

        Log.d("TAG", "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        fragmentTransaction = getFragmentManager().beginTransaction();

        switch (item.getItemId()){

            case R.id.item_1:

                fragmentTransaction.replace(R.id.fragment, fragment_1);
                    break;

            case R.id.item_2:

                //fragmentTransaction.add(R.id.fragment, fragment_2);
                fragmentTransaction.replace(R.id.fragment, fragment_2);
                break;

            case R.id.item_3:

                //fragmentTransaction.add(R.id.fragment, fragment_3);
                fragmentTransaction.replace(R.id.fragment, fragment_3);
                break;
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        return super.onOptionsItemSelected(item);
    }
}
