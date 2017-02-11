package com.example.aaronpries.herds_social_app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 04-06-2015.
 */
public class EventsFragment extends Fragment {


//VARIABLES
    private RecyclerView mBlogList;
    private FirebaseDatabase database;
    private DatabaseReference ref;


    public EventsFragment() {
        // Required empty public constructor
    }

   //DELETE THIS IF IT DOESN'T POPULATE
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.events_fragment,container,false);
        final View v = inflater.inflate(R.layout.events_fragment,container,false);
        //set up Recyclerv]View
//        RecyclerView mBlogList = (RecyclerView) v.findViewById(R.id.blog_list);
//        //Connect to Firebase
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        //Custom subclass for FireBaseAdapter


        return v;


    }



}
