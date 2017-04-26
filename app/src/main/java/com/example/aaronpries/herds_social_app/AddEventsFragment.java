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
public class AddEventsFragment extends Fragment implements  View.OnClickListener{


//    private ImageButton mSelectImage;
    private EditText mTitle;
    private EditText mDesc;
    private EditText mDate;
    private Button mSubmit;
    private EditText mTime;
    private EditText mLocation;
    private EditText mURL;
    private int mYear;
    private int mDay;
    private int mMonth;
    private int mHour;
    private int mMinute;
    private Uri mImageUri = null;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;
    private MaterialBetterSpinner mGroups;
    private MaterialBetterSpinner mCategory;

    private static  final int GALLERY_REQUEST = 1;
    private static String TAG = "EventsFragment";

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference myDatabase;
    private StorageReference mStorageImage;



//CREATE OPTIONS FOR DROPDOWN MENU (GROUP)
    String[] mGroupList = {"Alpha Phi", "Alpha Sigma Alpha", "Delta Sigma Pi", "Geeks Who Drink", "Sigma Chi"};
    String[] mCategoryList = {"Music", "Food", "Night Life", "Community", "Philanthropy"};

    public AddEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.add_events_fragment, container, false);
        v.setTag(TAG);

        myDatabase = FirebaseDatabase.getInstance().getReference();

//MAKE REFERENCE TO DATABASE STORAGE FOR IMAGES (FOLDER)
        mStorage = FirebaseStorage.getInstance().getReference();
        mStorageImage = FirebaseStorage.getInstance().getReference().child("eventImages");

//MAKE REFERENCE TO DATABASE ROOT DIRECTORY
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Data");


//MAP FIELDS TO LAYOUT
        //mSelectImage = (ImageButton)v.findViewById(R.id.fieldImageButton);
        mTitle = (EditText)v.findViewById(R.id.fieldEventTitle);
        mDesc = (EditText)v.findViewById(R.id.fieldEventDescription);
        mDate = (EditText)v.findViewById(R.id.fieldEventDate);
        mSubmit = (Button)v.findViewById(R.id.btnAddEvent);
        mGroups = (MaterialBetterSpinner)v.findViewById(R.id.fieldGroup);
        mCategory = (MaterialBetterSpinner)v.findViewById(R.id.fieldCategory);
        mURL = (EditText)v.findViewById(R.id.fieldURL);
        mTime = (EditText)v.findViewById(R.id.fieldTime);
        mLocation = (EditText)v.findViewById(R.id.fieldLocation);

        mDate.setOnClickListener(this);
        mTime.setOnClickListener(this);



//PROGRESS BAR
//        mProgress =  new ProgressDialog(getActivity());

//DROPDOWN MENU (GROUPS)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, mGroupList);
        MaterialBetterSpinner textView = (MaterialBetterSpinner)v.findViewById(R.id.fieldGroup);
        textView.setAdapter(adapter);

//DROPDOWN MENU (CATEGORY)
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, mCategoryList);
        MaterialBetterSpinner textView2 = (MaterialBetterSpinner)v.findViewById(R.id.fieldCategory);
        textView2.setAdapter(adapter2);



//
////CLICK LISTENER FOR IMAGE SELECTION
//        mSelectImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }
//
////REQUEST IMAGES FROM GALLERY
//            @Override
//            public void onClick(View view) {
//
//                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                galleryIntent.setType("image/*");
//                startActivityForResult(galleryIntent, GALLERY_REQUEST);
//
//            }
//        });

//CLICK LISTENER FOR SUBMIT BUTTON

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startEventPost();

            }
        });


        return v;
    }

    @Override
    public void onClick(View v) {

        if (v == mDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            mDate.setText((monthOfYear + 1) + "/" + (dayOfMonth) + "/" + year % 100);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == mTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            mTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

    }


    private void startEventPost() {

//        mProgress.setMessage("Posting Event...");
//        mProgress.show();

//GET DATA FROM FIELDS
        final String title = mTitle.getText().toString().trim();
        final String desc = mDesc.getText().toString().trim();
        final String date = mDate.getText().toString().trim();
        final String group = mGroups.getText().toString().trim();
        final String category = mCategory.getText().toString().trim();
        final String image = mURL.getText().toString().trim();
        final String time = mTime.getText().toString().trim();
        final String location = mLocation.getText().toString().trim();


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

        if(!TextUtils.isEmpty(title) &&
                !TextUtils.isEmpty(title) &&
                !TextUtils.isEmpty(desc) &&
                !TextUtils.isEmpty(date) &&
                !TextUtils.isEmpty(group) &&
                !TextUtils.isEmpty(category) &&
                !TextUtils.isEmpty(image) &&
                !TextUtils.isEmpty(time) &&
                !TextUtils.isEmpty(location)){


            ModelAddEvent modelAdd = new ModelAddEvent(title,desc,category,date,image,time,location,group);
            DatabaseReference newPost = mDatabase.push();
            newPost.setValue(modelAdd);

            Toast.makeText(getActivity(), "Event Created", Toast.LENGTH_SHORT).show();



            EventsFragment fragment = new EventsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment);
            fragmentTransaction.commit();

        }

        else{

            Toast.makeText(getActivity(), "Oops, you forgot something!", Toast.LENGTH_SHORT).show();

        }


                    //@SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();



//                   mProgress.dismiss();

        }



//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
//
//            Uri mImageUri = data.getData();
//
//            StorageReference filepath = mStorage.child("image").child(mImageUri.getLastPathSegment());
//
//            mSelectImage.setImageURI(mImageUri);
//
//        }
//    }


}
