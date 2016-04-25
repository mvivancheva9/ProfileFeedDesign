package com.example.admin.designtask.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.designtask.R;
import com.example.admin.designtask.common.MyApplication;
import com.example.admin.designtask.models.PostModel;
import com.example.admin.designtask.view.adapter.FeedListAdapter;

import java.util.ArrayList;

public class FeedFragment extends Fragment {
    RecyclerView recyclerView;
    Context context;
    ArrayList<PostModel> postModels;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        postModels = new ArrayList<>();
        context = getActivity();

        postModels = MyApplication.getInstance().getSqlDb().getAllPosts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feed_fragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        setupRecyclerView(recyclerView);
        return rootView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new FeedListAdapter(postModels, R.layout.feed_list_row));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}

