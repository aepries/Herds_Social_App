package com.example.aaronpries.herds_social_app;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


import java.util.Calendar;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddGroupFragment extends Fragment {


    //    private ImageButton mSelectImage;
    private EditText mName;
    private EditText mBio;
    private Button mSubmit;
    private EditText mURL;

    private Uri mImageUri = null;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;


    private static  final int GALLERY_REQUEST = 1;
    private static String TAG = "AddGroupFragment";

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference myDatabase;
    private StorageReference mStorageImage;



    public AddGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.add_group_fragment, container, false);
        v.setTag(TAG);

        myDatabase = FirebaseDatabase.getInstance().getReference();

//MAKE REFERENCE TO DATABASE STORAGE FOR IMAGES (FOLDER)
        mStorage = FirebaseStorage.getInstance().getReference();
        mStorageImage = FirebaseStorage.getInstance().getReference().child("groupImages");

//MAKE REFERENCE TO DATABASE ROOT DIRECTORY
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Groups");


//MAP FIELDS TO LAYOUT
        //mSelectImage = (ImageButton)v.findViewById(R.id.fieldImageButton);
        mName = (EditText)v.findViewById(R.id.fieldGroupName);
        mBio = (EditText)v.findViewById(R.id.fieldGroupBio);
        mSubmit = (Button)v.findViewById(R.id.btnAddGroup);
        mURL = (EditText)v.findViewById(R.id.fieldGroupURL);




//        final Calendar c = Calendar.getInstance();
//        System.out.println("----------------------------------" + c.toString() + "-------------------------------------");








//CLICK LISTENER FOR SUBMIT BUTTON
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startGroupPost();

            }
        });


        return v;
    }


    private void startGroupPost() {


//GET DATA FROM FIELDS
        final String name = mName.getText().toString().trim();
        final String bio = mBio.getText().toString().trim();
        final String image = mURL.getText().toString().trim();



//        StorageReference filepath = mStorageImage.child(mImageUri.getLastPathSegment());
//         filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//             @Override
//             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                 @SuppressWarnings("VisibleForTests") String image = taskSnapshot.getDownloadUrl().toString();
//
//                 Toast.makeText(getActivity(), "it worked", Toast.LENGTH_SHORT).show();
//
//             }
//         });

        //MAKING SURE ALL FIELDS ARE VALID

        if(!TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(bio) &&
                !TextUtils.isEmpty(image)){


            ModelAddGroup modelAdd = new ModelAddGroup(name,image,bio);
            DatabaseReference newPost = mDatabase.push();
            newPost.setValue(modelAdd);

            Toast.makeText(getActivity(), "Group Created", Toast.LENGTH_SHORT).show();



            GroupsFragment fragment = new GroupsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment);
            fragmentTransaction.commit();

        }

        else{

            Toast.makeText(getActivity(), "Oops, you forgot something!", Toast.LENGTH_SHORT).show();

        }



    }
}
