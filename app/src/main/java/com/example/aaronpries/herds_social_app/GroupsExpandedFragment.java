package com.example.aaronpries.herds_social_app;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsExpandedFragment extends Fragment {


    public GroupsExpandedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.groups_expanded_fragment, container, false);

        Bundle bundle = getArguments();

        String name = bundle.getString("name");
        String bio = bundle.getString("bio");
        String image = bundle.getString("image");


        TextView nameText = (TextView) rootview.findViewById(R.id.nameText);
        TextView bioText = (TextView) rootview.findViewById(R.id.bioText);
        ImageView imageView = (ImageView) rootview.findViewById(R.id.imagePost);

        bioText.setText(bio);
        nameText.setText(name);
        Picasso.with(getActivity().getApplicationContext()).load(image).into(imageView);

        return rootview;
    }

}
