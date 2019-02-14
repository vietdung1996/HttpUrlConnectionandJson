package com.vietdung.httpurlconnectionandjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements onTaskListerner {
    private String mURI = "https://api.github.com/users/google/repos";
    private RecyclerView mRecyclerViewMain;
    private List<User> mUsers;
    private UserAdapter mUserAdapter;
    private CatalogClients mCatalogClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onTaskCompletion(List<User> user) {
        mUsers = user;
        mUserAdapter.setUsers(mUsers);
    }

    private void initView() {
        mRecyclerViewMain = findViewById(R.id.recycler_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewMain.setLayoutManager(layoutManager);
        mCatalogClients = new CatalogClients(this);
        mCatalogClients.execute(mURI);
        mUserAdapter = new UserAdapter(MainActivity.this, mUsers);
        mRecyclerViewMain.setAdapter(mUserAdapter);
    }
}
