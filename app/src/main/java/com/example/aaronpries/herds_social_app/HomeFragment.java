package com.example.aaronpries.herds_social_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private ImageView profile_pic = null;
    private TextView name = null;
    private TextView email = null;
    private TextView locale = null;
    private Button logoutButton = null;
    private Profile profile = null;
    private ListView groups = null;
//    private DatabaseReference mFirebaseDatabaseReference;
//    private static String TAG = "HomeFragment";
//    public static final String DATA = "Users";
//    private DatabaseReference myDatabase;
//    private StorageReference mStorageImage;
//    private DatabaseReference mDatabase;
//    private StorageReference mStorage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {







        View view = inflater.inflate(R.layout.home_fragment, container, false);
        profile_pic = (ImageView) view.findViewById(R.id.profile_pic);
        name = (TextView) view.findViewById(R.id.tv_name);
//        email = (TextView) view.findViewById(R.id.tv_email);
        locale = (TextView) view.findViewById(R.id.tv_id);
        logoutButton = (Button) view.findViewById(R.id.logout_button);
//        groups = (ListView) view.findViewById(R.id.list_groups);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            profile = (Profile) bundle.getParcelable(ProfileFragment.PARCEL_KEY);
        } else {
            profile = Profile.getCurrentProfile();
        }




        name.setText(profile.getName());


        locale.setText("ID: " + profile.getId());

        Picasso.with(getActivity())
                .load(profile.getProfilePictureUri(1200, 800).toString())
                .into(profile_pic);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                profile = null;
            }
        });

    }



    private void logout() {
        LoginManager.getInstance().logOut();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.frame, new ProfileFragment());
        fragmentTransaction.commit();
    }


}
