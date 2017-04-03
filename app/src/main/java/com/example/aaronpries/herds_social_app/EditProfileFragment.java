package com.example.aaronpries.herds_social_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    private Button xSubmit;
    private EditText xName;
    private EditText xAge;
    private EditText xBio;
    private EditText xImage;
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
        xAge = (EditText)v.findViewById(R.id.xage);
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
        final String yage = xAge.getText().toString().trim();
        final String yimage = xImage.getText().toString().trim();

        Bundle bundle = new Bundle();
        bundle.putString("bio", ybio);
        bundle.putString("name", yname);
        bundle.putString("image", yimage);
        bundle.putString("age", yage);

        ProfileFragment fragment = new ProfileFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();

    }

}
