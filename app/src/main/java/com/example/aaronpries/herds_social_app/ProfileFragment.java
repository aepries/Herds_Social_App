package com.example.aaronpries.herds_social_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ProfileFragment extends Fragment {

    private CallbackManager callbackManager = null;
    private AccessTokenTracker mtracker = null;
    private ProfileTracker mprofileTracker = null;
    private Profile profile = null;

    private DatabaseReference mFirebaseDatabaseReference;
    private static String TAG = "HomeFragment";
    public static final String DATA = "Users";
    private DatabaseReference myDatabase;
    private StorageReference mStorageImage;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;

    public static final String PARCEL_KEY = "parcel_key";

    private LoginButton loginButton;
    private Button RegLoginButton;

    FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            myDatabase = FirebaseDatabase.getInstance().getReference();

//MAKE REFERENCE TO DATABASE STORAGE FOR IMAGES (FOLDER)
        mStorage = FirebaseStorage.getInstance().getReference();
        mStorageImage = FirebaseStorage.getInstance().getReference().child("UserImages");

//MAKE REFERENCE TO DATABASE ROOT DIRECTORY
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");




            Profile profile = Profile.getCurrentProfile();
            homeFragment(profile);

            startLogUser();



        }

        @Override
        public void onCancel() {

            Log.v("LoginActivity", "cancel");

        }

        @Override
        public void onError(FacebookException error) {


        }
    };

    private void startLogUser() {



        profile = Profile.getCurrentProfile();

            final String name = profile.getName().trim();
            final String image = profile.getProfilePictureUri(500, 500).toString().trim();
            final String id = profile.getId().trim();


            ModelAddUser modelAdd = new ModelAddUser(name,image,id);
            DatabaseReference newUser = mDatabase.push();
            newUser.setValue(modelAdd);


            Toast.makeText(getActivity(), "User Added", Toast.LENGTH_SHORT).show();

        }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();


        mtracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                Log.v("AccessTokenTracker", "oldAccessToken=" + oldAccessToken + "||" + "CurrentAccessToken" + currentAccessToken);
            }
        };


        mprofileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

                Log.v("Session Tracker", "oldProfile=" + oldProfile + "||" + "currentProfile" + currentProfile);
                homeFragment(currentProfile);

            }
        };

        mtracker.startTracking();
        mprofileTracker.startTracking();
    }


    private void homeFragment(Profile profile) {

        if (profile != null) {
            Bundle mBundle = new Bundle();
            mBundle.putParcelable(PARCEL_KEY, profile);
            HomeFragment hf = new HomeFragment();
            hf.setArguments(mBundle);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.replace(R.id.frame, new HomeFragment());
            fragmentTransaction.commit();
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.profile_fragment, container, false);

        RegLoginButton = (Button) v.findViewById(R.id.mLogin_button);

        RegLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSignUp();

            }


        });

        return v;
    }

    private void startSignUp() {

        Toast.makeText(getActivity(), "IT WORKED", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);



        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

        callbackManager = CallbackManager.Factory.create();

        // If using in a fragment
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {
        super.onStop();
        mtracker.stopTracking();
        mprofileTracker.stopTracking();
    }


    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public boolean isLoggedOut() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken == null;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isLoggedIn()) {
            loginButton.setVisibility(View.INVISIBLE);

            Profile profile = Profile.getCurrentProfile();
            homeFragment(profile);
        }


        else if (isLoggedOut()){
            loginButton.setVisibility(View.VISIBLE);
        }

    }

}
