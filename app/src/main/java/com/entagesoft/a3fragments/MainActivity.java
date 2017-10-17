package com.entagesoft.a3fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
import static com.entagesoft.a3fragments.R.layout.fragment_5;

public class MainActivity extends AppCompatActivity {

    Fragment fragment_1;
    Fragment fragment_2;
    Fragment fragment_3;
    Fragment fragment_4;
    Fragment fragment_5;
    FragmentTransaction fragmentTransaction;
    public static long EDIT_REC_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startService(new Intent(this, LogService.class).putExtra("string", "onCreate"));

        fragment_1 = new Fragment1();
        fragment_2 = new Fragment2();
        fragment_3 = new Fragment3();
        fragment_4 = new Fragment4();
        fragment_5 = new Fragment5();


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

            case R.id.item_4:

                //fragmentTransaction.add(R.id.fragment, fragment_3);
                fragmentTransaction.replace(R.id.fragment, fragment_4);
                break;

            case R.id.item_5:

                //fragmentTransaction.add(R.id.fragment, fragment_3);
                fragmentTransaction.replace(R.id.fragment, fragment_5);
                break;
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent();
        intent.putExtra("turnOff", true);
        intent.putExtra("string", "onDestroy");

        stopService(new Intent(this, LogService.class));

        //startService(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //startService(new Intent(this, LogService.class).putExtra("string", "onStart"));
    }

    @Override
    protected void onStop() {
        super.onStop();

        //startService(new Intent(this, LogService.class).putExtra("string", "onStop"));
    }

    @Override
    protected void onPause() {
        super.onPause();

        //startService(new Intent(this, LogService.class).putExtra("string", "onPause"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        //startService(new Intent(this, LogService.class).putExtra("string", "onResume"));
    }
}
