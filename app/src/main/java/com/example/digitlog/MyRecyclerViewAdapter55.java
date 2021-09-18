package com.example.digitlog;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter55 extends RecyclerView.Adapter<MyRecyclerViewAdapter55.ViewHolder> {
    private ArrayList<String> mExampleList;
    private int textSize;

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter55(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.textSize = 10;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new MyRecyclerViewAdapter55.ViewHolder(view);

      // View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
      //  return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mExampleList.get(position);
        holder.myTextView.setText(animal);

        holder.myTextView.setSelected(holder.myTextView.isSelected()?true:false);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        try {
            return mExampleList.size();
            // return category.size();
        }catch (Exception ex) {
            return 0;
        }
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);



        }




        @Override
        public void onClick(View view) {


            if (mClickListener != null){ mClickListener.onItemClick(view, getAdapterPosition()); };
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mExampleList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public MyRecyclerViewAdapter55(ArrayList<String> exampleList) {
        mExampleList = exampleList;

    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    public void filterList(ArrayList<String> filteredList) {
       try{
           mExampleList = filteredList;
           notifyDataSetChanged();
       }catch (Exception e){

       }

    }
}