package com.example.admin.designtask.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.designtask.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView text;
    public TextView location;
    public TextView author;
    public ImageView avatar;
    public TextView likes;
    public ImageButton buttonLike;

    public ViewHolder(View itemView) {
        super(itemView);
        avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
        location = (TextView) itemView.findViewById(R.id.tvLocation);
        author = (TextView) itemView.findViewById(R.id.tvAuthor);
        text = (TextView) itemView.findViewById(R.id.tvText);
        likes = (TextView) itemView.findViewById(R.id.tvLikes);
        buttonLike = (ImageButton) itemView.findViewById(R.id.ibLike);
    }
}