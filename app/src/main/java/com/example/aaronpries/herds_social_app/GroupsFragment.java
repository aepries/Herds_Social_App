package com.example.aaronpries.herds_social_app;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {
    public String postKey;


    public static class GroupViewHolder extends RecyclerView.ViewHolder{
        public TextView groupTitle;
        public ImageView groupImage;
        //public TextView groupShortBio;
        View mView;

        public GroupViewHolder(View v){
            super(v);
            mView = v;
            groupTitle = (TextView)mView.findViewById(R.id.name);
            groupImage = (ImageView)mView.findViewById(R.id.image);
            //groupShortBio = (TextView)mView.findViewById(R.id.shortbio);
        }
//
//        public TextView getgroupTitle() {
//            return groupTitle;
//        }
//
//        public void setgroupTitle(TextView groupTitle) {
//            this.groupTitle = groupTitle;
//        }
//
//        public TextView getgroupTitle() {
//            return groupTitle;
//        }
//
        public void setTitle(String title){
            TextView group_name = (TextView)mView.findViewById(R.id.title);
            group_name.setText(title);

        }
//
//        public setShortBio(String shortbio){
//            TextView group_name = (TextView)mView.findViewById(R.id.shortbio);
//            group_name.setText(shortbio);
//        }
    }

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<ModelClass, GroupsFragment.GroupViewHolder> mFirebaseAdapter;
    private RecyclerView gEventRecyclerView;
    private LinearLayoutManager gLinearLayoutManager;
    private static String TAG = "GroupsFragment";
    public static final String GROUPS = "Groups";


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
        final View v = inflater.inflate(R.layout.groups_fragment,container,false);
        v.setTag(TAG);
//RV
        gEventRecyclerView = (RecyclerView)v.findViewById(R.id.groups_list);
        //LLM
        gLinearLayoutManager = new LinearLayoutManager(getActivity());
        gEventRecyclerView.setLayoutManager(gLinearLayoutManager);
        //DB
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<ModelClass, GroupsFragment.GroupViewHolder>(
                ModelClass.class,
                R.layout.groupscard_layout,
                GroupsFragment.GroupViewHolder.class,
                mFirebaseDatabaseReference.child(GROUPS))
        {
            @Override
            protected void populateViewHolder(GroupsFragment.GroupViewHolder viewHolder, ModelClass model, final int position) {

                final String post_key = getRef(position).getKey();
                //final String group_name = getRef(position).getKey().getClass().getName();



                viewHolder.groupTitle.setText(model.getName());
                //viewHolder.groupShortBio.setText(model.getShortBio());
                Picasso.with(getActivity().getApplicationContext())
                        .load(model.getImage())
                        .fit()
                        .into(viewHolder.groupImage);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        //final String theTitle = getRef(position).getRoot().child(DATA).child(post_key).child(getItem())
                        //Toast.makeText(getActivity(), post_key, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(), theTitle, Toast.LENGTH_SHORT).show();

                        setPostKey(post_key);

                        mFirebaseDatabaseReference.child(GROUPS).child(post_key).addValueEventListener(new ValueEventListener() {

                            String group_info, group_name, group_image;

                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                System.out.println(snapshot.getValue());
                                //group_info = snapshot.child("shortbio").getValue().toString();
                                group_name = snapshot.child("name").getValue().toString();
                                group_image = snapshot.child("image").getValue().toString();

                                Bundle bundle = new Bundle();
                                //bundle.putString("shortbio", group_info);
                                bundle.putString("name", group_name);
                                bundle.putString("image", group_image);

                                FragmentManager fragM = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragT = fragM.beginTransaction();

                                GroupsExpandedFragment expand = new GroupsExpandedFragment();
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

        gEventRecyclerView.setLayoutManager(gLinearLayoutManager);
        gEventRecyclerView.setAdapter(mFirebaseAdapter);

        return v;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }




}