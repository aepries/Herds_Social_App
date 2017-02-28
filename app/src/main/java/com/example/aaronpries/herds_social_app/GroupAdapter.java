package com.example.aaronpries.herds_social_app;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {
    private String[] mDataset;
    ArrayList<ModelClass> modelClass;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        //public TextView mmTextView;
        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
            //mTextView = (TextView) v.findViewById(R.id.tv_blah);
            //Use this for description?
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GroupAdapter(String[] myDataset) {
        mDataset = myDataset;
    }




    // Create new views (invoked by the layout manager)
    @Override
    public GroupAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
// create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);


// set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}