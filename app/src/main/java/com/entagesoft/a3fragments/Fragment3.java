package com.entagesoft.a3fragments;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Fragment3 extends Fragment {

    TextView txtName;
    TextView txtEmail;

    View view;
    public CallbackManager callbackManager;
    public ProfilePictureView profileImage;
    public LoginButton login_button;
    ProfileTracker profileTracker;
    FragmentTransaction fragmentTransaction;

    FacebookCallback<LoginResult> callback =  new FacebookCallback<LoginResult>(){

        @Override
        public void onSuccess(final LoginResult loginResult) {

            Toast.makeText(getApplicationContext(), "Success login", Toast.LENGTH_LONG).show();


            if(Profile.getCurrentProfile() == null) {
                profileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                        Log.v("facebook - profile", currentProfile.getFirstName());

                        txtName.setText(currentProfile.getName());
                        profileImage.setProfileId(currentProfile.getId());
                        profileTracker.stopTracking();
                    }
                };
            }
            else {

                Profile profile = Profile.getCurrentProfile();
                txtName.setText(profile.getName().toString());
                profileImage.setProfileId(profile.getId());
            }

            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {


                            Log.e("onSuccess", "--------" + loginResult.getAccessToken());
                            String em = null;
                            try {

                                em = object.getString("email");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            txtEmail.setText(em);
                            Log.d("JSON ", object.toString());
                            Log.d("GraphResponse", "-------------" + response.toString());
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,gender,birthday,email");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {

            Toast.makeText(getApplicationContext(), "Cancel login", Toast.LENGTH_LONG).show();
            //txtName.setText("Login Cancelled ");
        }

        @Override
        public void onError(FacebookException error) {

            Toast.makeText(getApplicationContext(), "Error login", Toast.LENGTH_LONG).show();
            //txtName.setText("Login Error " + error.getMessage());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_3, container, false);

        FacebookSdk.sdkInitialize(getApplicationContext());
        profileImage = (ProfilePictureView) view.findViewById(R.id.profilePicture);
        txtName = (TextView) view.findViewById(R.id.txtName);
        txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        callbackManager = CallbackManager.Factory.create();
        login_button = (LoginButton) view.findViewById(R.id.login_button);
        login_button.setReadPermissions(Arrays.asList("email", "user_photos", "user_about_me",
                "user_location", "user_friends", "user_birthday"));
        login_button.setFragment(this);
        login_button.registerCallback(callbackManager, callback);
        initSP();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void initSP(){

//        nameText = (TextView) view.findViewById(R.id.textView);
//        surText = (TextView) view.findViewById(R.id.textView2);
//        emailText = (TextView) view.findViewById(R.id.textView3);
//        telText = (TextView) view.findViewById(R.id.textView4);
//
//        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
//
//        nameText.setText(sp.getString("name", null));
//        surText.setText(sp.getString("surname", null));
//        emailText.setText(sp.getString("email", null));
//        telText.setText(sp.getString("telephone", null));
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
