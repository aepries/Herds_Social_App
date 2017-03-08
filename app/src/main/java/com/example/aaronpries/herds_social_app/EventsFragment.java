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

import static com.example.aaronpries.herds_social_app.R.id.category;

/**
 * Created by Admin on 04-06-2015.
 */
public class EventsFragment extends Fragment {
    public String postKey;


    public static class EventViewHolder extends RecyclerView.ViewHolder{
        public TextView eventTitle;
        public ImageView eventImage;
        public ImageView eventCategory;
        public TextView eventDate;
        public TextView eventTime;
        View mView;

        public EventViewHolder(View v){
            super(v);
            mView = v;
            eventTitle = (TextView)mView.findViewById(R.id.title);
            eventImage = (ImageView)mView.findViewById(R.id.image);
            eventCategory = (ImageView)mView.findViewById(category);
            eventDate = (TextView)mView.findViewById(R.id.date);
            eventTime = (TextView)mView.findViewById(R.id.time);
        }

        public TextView getEventTitle() {
            return eventTitle;
        }

        public void setEventTitle(TextView eventTitle) {
            this.eventTitle = eventTitle;
        }



//        public void setTitle(String title){
//            TextView post_title = (TextView)mView.findViewById(R.id.title);
//            post_title.setText(title);
//
//        }
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

//RecyclerView
        mEventRecyclerView = (RecyclerView)v.findViewById(R.id.events_list);

//Layout Manager
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mEventRecyclerView.setLayoutManager(mLinearLayoutManager);

//Database reference
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

//Keep DB synced offline
        mFirebaseDatabaseReference.keepSynced(true);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<ModelClass, EventViewHolder>(
                ModelClass.class,
                R.layout.design_row,
                EventViewHolder.class,
                mFirebaseDatabaseReference.child(DATA))
        {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, ModelClass model, final int position) {

                final String post_key = getRef(position).getKey();




                viewHolder.eventDate.setText(model.getDate());
                viewHolder.eventTitle.setText(model.getTitle());
                viewHolder.eventTime.setText(model.getTime());
                Picasso.with(getActivity().getApplicationContext())
                        .load(model.getImage())
                        .fit()
                        .into(viewHolder.eventImage);


                switch (model.getCategory()){
                    case "Music": {
                        viewHolder.eventCategory.setImageResource(R.drawable.ic_action_music_1);
                        break;
                    }
                    case "Greek": {
                        viewHolder.eventCategory.setImageResource(R.drawable.ic_account_balance);
                        break;
                    }
                    case "Night Life": {
                        viewHolder.eventCategory.setImageResource(R.drawable.ic_local_bar);
                        break;
                    }
                    case "Food": {
                        viewHolder.eventCategory.setImageResource(R.drawable.ic_local_dining);
                        break;
                    }
                    case "Community": {
                        viewHolder.eventCategory.setImageResource(R.drawable.ic_action_users);
                        break;
                    }
                    case "Philanthropy": {
                        viewHolder.eventCategory.setImageResource(R.drawable.ic_public);
                        break;
                    }
                    default:
                        break;
                }



                viewHolder.mView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        //final String theTitle = getRef(position).getRoot().child(DATA).child(post_key).child(getItem())
                        //Toast.makeText(getActivity(), post_key, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(), theTitle, Toast.LENGTH_SHORT).show();

                        setPostKey(post_key);

                        mFirebaseDatabaseReference.child(DATA).child(post_key).addValueEventListener(new ValueEventListener() {

                            String post_info, post_title, post_image, post_category, post_time, post_date, post_location, post_group;

                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                System.out.println(snapshot.getValue());
                                post_info = snapshot.child("info").getValue().toString();
                                post_title = snapshot.child("title").getValue().toString();
                                post_image = snapshot.child("image").getValue().toString();
                                post_category = snapshot.child("category").getValue().toString();
                                post_date = snapshot.child("date").getValue().toString();
                                post_time = snapshot.child("time").getValue().toString();
                                post_group = snapshot.child("group").getValue().toString();
                                post_location = snapshot.child("location").getValue().toString();

                                Bundle bundle = new Bundle();
                                bundle.putString("info", post_info);
                                bundle.putString("title", post_title);
                                bundle.putString("image", post_image);
                                bundle.putString("date", post_date);
                                bundle.putString("time", post_time);
                                bundle.putString("location", post_location);
                                bundle.putString("group", post_group);
                                bundle.putString("category", post_category);

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
