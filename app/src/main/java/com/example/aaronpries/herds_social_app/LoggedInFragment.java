package com.example.aaronpries.herds_social_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoggedInFragment extends Fragment {


    public LoggedInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.logged_in_fragment, container, false);


        TextView nameText = (TextView) v.findViewById(R.id.logName);
        TextView BioText = (TextView) v.findViewById(R.id.logBio);
        ImageView ImageView = (ImageView) v.findViewById(R.id.logImage);
        Button logout = (Button) v.findViewById(R.id.logoutbtn);
        Button edit = (Button) v.findViewById(R.id.editbtn);


        Bundle Mbundle = getArguments();

        String name = Mbundle.getString("name");
        String bio = Mbundle.getString("bio");
        String image = Mbundle.getString("image");



        nameText.setText(name);
        BioText.setText(bio);
        Picasso.with(getActivity().getApplicationContext()).load(image).into(ImageView);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProfileFragment Pfragment = new ProfileFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,Pfragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditProfileFragment Efragment = new EditProfileFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,Efragment);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Edit Profile", Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }

}
