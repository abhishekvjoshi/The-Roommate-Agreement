package com.dreams.theroommateagreement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 3/25/18.
 */

public class AdPostingActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdAdapter.AdAdapterListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<RoommateAdvertisement> advertisements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_postings);
        mRecyclerView = findViewById(R.id.rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        getDataSet();
        mAdapter = new AdAdapter(this, advertisements, this);
        mRecyclerView.setAdapter(mAdapter);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        FloatingActionButton createAdButton = (FloatingActionButton) findViewById(R.id.createAdButton);
        createAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdPostingActivity.this, AdCreatorActivity.class));

            }
        });

        // show loader and fetch messages
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getDataSet();
                    }
                }
        );
    }

    private List<RoommateAdvertisement> getDataSet() {
        for (int i = 0; i < 100; i++) {
            advertisements.add(new RoommateAdvertisement(String.format("Name: %d", i),
                                              String.format("Address: %d", i)));
        }
        return advertisements;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onIconImportantClicked(int position) {
        RoommateAdvertisement advertisement = advertisements.get(position);
        advertisement.setImportant(!advertisement.isImportant());
        advertisements.set(position, advertisement);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRowLongClicked(int position) {

    }
}
