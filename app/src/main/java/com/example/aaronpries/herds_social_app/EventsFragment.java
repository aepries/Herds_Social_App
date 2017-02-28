package com.example.aaronpries.herds_social_app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Admin on 04-06-2015.
 */
public class EventsFragment extends Fragment {
    public String postKey;


    public static class EventViewHolder extends RecyclerView.ViewHolder{
        public TextView eventTitle;
        public ImageView eventImage;
        View mView;

        public EventViewHolder(View v){
            super(v);
            mView = v;
            eventTitle = (TextView)mView.findViewById(R.id.title);
            eventImage = (ImageView)mView.findViewById(R.id.image);
        }

        public TextView getEventTitle() {
            return eventTitle;
        }

        public void setEventTitle(TextView eventTitle) {
            this.eventTitle = eventTitle;
        }

//        public TextView getEventTitle() {
//            return eventTitle;
//        }

        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.title);
            post_title.setText(title);

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


//    public EventsFragment() {
//        // Required empty public constructor
//    }









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
                R.layout.design_row,
                EventViewHolder.class,
                mFirebaseDatabaseReference.child(DATA))
        {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, ModelClass model, final int position) {

                final String post_key = getRef(position).getKey();
                //final String post_title = getRef(position).getKey().getClass().getName();



                viewHolder.eventTitle.setText(model.getTitle());
                Picasso.with(getActivity().getApplicationContext())
                        .load(model.getImage())
                        .fit()
                        .into(viewHolder.eventImage);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        //final String theTitle = getRef(position).getRoot().child(DATA).child(post_key).child(getItem())
                        //Toast.makeText(getActivity(), post_key, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(), theTitle, Toast.LENGTH_SHORT).show();

                        setPostKey(post_key);

                        mFirebaseDatabaseReference.child(DATA).child(post_key).addValueEventListener(new ValueEventListener() {

                            String post_info, post_title, post_image;

                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                System.out.println(snapshot.getValue());
                                post_info = snapshot.child("info").getValue().toString();
                                post_title = snapshot.child("title").getValue().toString();
                                post_image = snapshot.child("image").getValue().toString();

                                Bundle bundle = new Bundle();
                                bundle.putString("info", post_info);
                                bundle.putString("title", post_title);
                                bundle.putString("image", post_image);

                                FragmentManager fragM = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragT = fragM.beginTransaction();

                                EventsExpandedFragment expand = new EventsExpandedFragment();
                                expand.setArguments(bundle);

                                fragT.replace(R.id.frame, expand);
                                fragT.addToBackStack("");
                                fragT.commit();


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                });
            }


        };

        mEventRecyclerView.setLayoutManager(mLinearLayoutManager);
        mEventRecyclerView.setAdapter(mFirebaseAdapter);

        return v;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }




}
