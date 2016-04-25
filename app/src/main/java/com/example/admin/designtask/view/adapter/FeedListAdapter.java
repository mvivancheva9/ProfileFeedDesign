package com.example.admin.designtask.view.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.designtask.common.MyApplication;
import com.example.admin.designtask.models.PostModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeedListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<PostModel> posts;
    private int itemLayout;
    private int likesCount = 0;

    public FeedListAdapter(List<PostModel> posts, int itemLayout) {
        this.posts = posts;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PostModel item = posts.get(position);
        if (item.getPostText() == null) {
            holder.text.setText("");
        } else {
            holder.text.setText(item.getPostText().toString());

            holder.location.setText(item.getPostLocation().toString());
        }
        Picasso picasso = new Picasso.Builder(holder.avatar.getContext())
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        exception.printStackTrace();
                    }
                })
                .build();
        picasso.load("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSGyGAtgXWj2z29hfVuUVvQxKPBO75k4nxDUiBWd53op8Kg_4WrjnE3rBjIGQ")
                .fit()
                .into(holder.avatar);

        holder.likes.setText(item.getLikes().toString());
        holder.author.setText("Me and Myself");

        holder.itemView.setTag(item);

        holder.buttonLike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                likesCount = MyApplication.getInstance().getSqlDb().likePost(item.getId());
                String likesAsText = String.valueOf(likesCount);
                holder.likes.setText(likesAsText);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}