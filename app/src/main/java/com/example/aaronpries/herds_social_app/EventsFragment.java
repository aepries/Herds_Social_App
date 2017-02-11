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

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        public TextView eventTitle;
        public ImageView eventImage;

        public EventViewHolder(View v){
            super(v);
            eventTitle = (TextView)itemView.findViewById(R.id.title);
            eventImage = (ImageView)itemView.findViewById(R.id.image);
        }
    }

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<ModelClass, EventViewHolder> mFirebaseAdapter;
    private RecyclerView mEventRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private static String TAG = "EventsFragment";
    public static final String DATA = "Data";


//    @Override
//    public void onStart() {
//        super.onStart();
//
//    }

    //VARIABLES
    private RecyclerView mBlogList;
    private FirebaseDatabase database;
    private DatabaseReference ref;


    public EventsFragment() {
        // Required empty public constructor
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.events_fragment,container,false);
        v.setTag(TAG);
//RV
        mEventRecyclerView = (RecyclerView)v.findViewById(R.id.events_list);
        //LLM
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mEventRecyclerView.setLayoutManager(mLinearLayoutManager);
        //DB
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<ModelClass, EventViewHolder>(
                ModelClass.class,
                R.layout.card_view_layout,
                EventViewHolder.class,
                mFirebaseDatabaseReference)
        {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, ModelClass model, int position) {
                viewHolder.eventTitle.setText(model.getTitle());
                //viewHolder.eventImag
            }
        };

        mEventRecyclerView.setLayoutManager(mLinearLayoutManager);
        mEventRecyclerView.setAdapter(mFirebaseAdapter);





        return v;


    }



}
