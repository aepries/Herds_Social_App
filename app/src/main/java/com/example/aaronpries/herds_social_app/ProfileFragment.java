package com.example.aaronpries.herds_social_app;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import static com.example.aaronpries.herds_social_app.R.id.imageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    Button mEditButton;
    TextView mName;
    TextView mAge;
    TextView mBio;
    TextView mGroup;
    ImageView mImage;

//    LoginButton loginButton;
//    TextView textView;
//    ImageView profilePicture;
//    CallbackManager callbackManager;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment,container,false);
//
//        FacebookSdk.getSdkVersion();
//        loginButton = (LoginButton)v.findViewById(R.id.login_button);
//        textView = (TextView)v.findViewById(R.id.loginTextView);
//        profilePicture = (ImageView)v.findViewById(R.id.profilePicture);
//        callbackManager = CallbackManager.Factory.create();
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                Toast.makeText(getContext(), "Connected to Facebook", Toast.LENGTH_SHORT).show();
//                textView.setText("Login Success");
//                profilePicture.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.profile));
//
//            }
//
//            @Override
//            public void onCancel() {
//
//                Toast.makeText(getContext(), "Login Cancelled", Toast.LENGTH_SHORT).show();
//                textView.setText("Login Cancelled");
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });

//        Bundle bundle = getArguments();
//        String name = bundle.getString("name");
//        String bio = bundle.getString("bio");
//        String image = bundle.getString("image");
//        String age = bundle.getString("age");



        mName = (TextView)v.findViewById(R.id.name);
        mAge = (TextView)v.findViewById(R.id.age);
        mBio = (TextView)v.findViewById(R.id.bio);
        mGroup = (TextView)v.findViewById(R.id.group);
        mImage = (ImageView)v.findViewById(R.id.profilePicture);
        mEditButton = (Button)v.findViewById(R.id.editButton);
//
//        mBio.setText(bio);
//        mName.setText(name);
//        mAge.setText(age);
//        Picasso.with(getActivity().getApplicationContext()).load(image).into(mImage);

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editProfile();

            }
        });


        return v;
    }

    private void editProfile() {

        EditProfileFragment fragment = new EditProfileFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//
//    }
}
