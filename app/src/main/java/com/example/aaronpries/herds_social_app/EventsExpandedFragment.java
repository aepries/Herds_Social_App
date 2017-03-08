package com.example.aaronpries.herds_social_app;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsExpandedFragment extends Fragment {




    public EventsExpandedFragment() {
        // Required empty public constructor
    }

@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.events_expanded_fragment, container, false);

        Bundle bundle = getArguments();

    String title = bundle.getString("title");
    String info = bundle.getString("info");
    String image = bundle.getString("image");
    String date = bundle.getString("date");
    String time = bundle.getString("time");
    String group = bundle.getString("group");
    String location = bundle.getString("location");

    TextView titleText = (TextView) rootview.findViewById(R.id.titlePost);
    TextView infoText = (TextView) rootview.findViewById(R.id.infoPost);
    ImageView imageView = (ImageView) rootview.findViewById(R.id.imagePost);
    TextView timeText = (TextView) rootview.findViewById(R.id.timePost);
    TextView dateText = (TextView) rootview.findViewById(R.id.datePost);
    TextView locationText = (TextView) rootview.findViewById(R.id.locationPost);
    TextView groupText = (TextView) rootview.findViewById(R.id.groupPost);


    titleText.setText(title);
    infoText.setText(info);
    timeText.setText(time);
    dateText.setText(date);
    locationText.setText(location);
    groupText.setText(group);
    Picasso.with(getActivity().getApplicationContext()).load(image).into(imageView);

//    Picasso.with(getActivity().getApplicationContext())
//            .load(image)
//            .fit()
//            .into(imageView);

    //Toast.makeText(getActivity(), image, Toast.LENGTH_SHORT).show();
    System.out.println(image);

        return rootview;

    }



}
