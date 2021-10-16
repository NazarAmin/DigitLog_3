package com.example.digitlog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<MyRecyclerViewAdapter2.ViewHolder> {

    private int textSize;
    private ArrayList<Faults_Trips> mExampleList;
    private List<String> category, urgency, user, comment, datetime, is_images;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter2(Context context, List<String> category, List<String> urgency, List<String> user,
                           List<String> comment, List<String> datetime, List<String> is_images) {

        this.mInflater = LayoutInflater.from(context);
        this.category = category;
        this.urgency = urgency;
        this.user = user;
        this.datetime = datetime;
        this.comment = comment;
        this.is_images = is_images;
        this.textSize = 10;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row2, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Faults_Trips current_item = mExampleList.get(position);
/*
        String category_s = category.get(position);
        String urgency_s = urgency.get(position);
        String user_s = user.get(position);
        String comment_s = comment.get(position);
        String datetime_s = datetime.get(position);
*/
        holder.category_tv.setText(current_item.getCategory());
        holder.urgency_tv.setText(current_item.getUrgency());
        holder.user_tv.setText(current_item.getUser_2());
        holder.comment_tv.setText(current_item.getComment());
        holder.datetime_tv.setText(current_item.getDatetime());
        holder.is_images.setText(current_item.getIs_image());
       /**
        holder.category_tv.setText(category_s);
        holder.urgency_tv.setText(urgency_s);
        holder.user_tv.setText(user_s);
        holder.comment_tv.setText(comment_s);
        holder.datetime_tv.setText(datetime_s);
        **/
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
        TextView datetime_tv, category_tv, urgency_tv, user_tv, comment_tv, is_images;

        ViewHolder(View itemView) {
            super(itemView);
            category_tv = itemView.findViewById(R.id.fault);
            datetime_tv = itemView.findViewById(R.id.date);
            urgency_tv = itemView.findViewById(R.id.urgency);
            comment_tv = itemView.findViewById(R.id.comment);
            user_tv = itemView.findViewById(R.id.user);
            is_images = itemView.findViewById(R.id.is_image_2);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

              if (mClickListener != null){
                  mClickListener.onItemClick(view, getAdapterPosition());
              };
        }
    }

    // convenience method for getting data at click position
    String getItem(List<String> category2, int id) {
       //return mExampleList.get(id);
        return category2.get(id);

    }


    String getItem2(List<String> category2,int id) {
        //return mExampleList.get(id);
        return category2.get(id);
    }

    String getItem3(List<String> category2,int id) {
        //return mExampleList.get(id);
        return category2.get(id);
    }
    String getItem4(List<String> category2,int id) {
        //return mExampleList.get(id);
        return category2.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    public MyRecyclerViewAdapter2(ArrayList<Faults_Trips> exampleList) {
        mExampleList = exampleList;

    }

    public void filterList(ArrayList<Faults_Trips> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }
}