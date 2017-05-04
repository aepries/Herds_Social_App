package com.example.aaronpries.herds_social_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    private Button xSubmit;
    private EditText xName;
    private EditText xBio;
    private EditText xImage;

//    private DatabaseReference mDatabase;
//    private DatabaseReference myDatabase;
//    private StorageReference mStorage;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

//    private EditText xGroup;

    public EditProfileFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.edit_profile_profile, container, false);


        xName = (EditText)v.findViewById(R.id.xname);
        xBio = (EditText)v.findViewById(R.id.xbio);
        xImage = (EditText)v.findViewById(R.id.ximage);
        xSubmit = (Button)v.findViewById(R.id.xsubmitProfile);

        xSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startEdit();

            }
        });

        return v;
    }


    private void startEdit() {

        final String yname = xName.getText().toString().trim();
        final String ybio = xBio.getText().toString().trim();
        final String yimage = xImage.getText().toString().trim();

        Bundle Mbundle = new Bundle();

        Mbundle.putString("name", yname);
        Mbundle.putString("bio", ybio);
        Mbundle.putString("image", yimage);


        LoggedInFragment fragment = new LoggedInFragment();
        fragment.setArguments(Mbundle);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();

    }

}
