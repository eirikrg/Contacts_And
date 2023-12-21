package com.eirikrg.contacts_and.ui.activities.users_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.eirikrg.domain.entities.api.ApiResponse;
import com.eirikrg.domain.entities.user.User;
import com.eirikrg.contacts_and.R;
import com.eirikrg.contacts_and.ui.activities.BaseActivity;
import com.eirikrg.contacts_and.ui.activities.user_detail_activity.UserDetailedActivity;
import com.eirikrg.contacts_and.ui.adapters.UserAdapter;
import com.eirikrg.contacts_and.utils.Constants;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UsersActivity extends AppCompatActivity implements BaseActivity {
    public RecyclerView usersRecyclerView;
    public CircularProgressIndicator circularProgressIndicator;
    public SwipeRefreshLayout swipeRefreshLayout;
    private UsersActivityViewModel usersActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        this.usersActivityViewModel = new ViewModelProvider(this).get(UsersActivityViewModel.class);

        this.circularProgressIndicator = findViewById(R.id.cpi_users);
        this.usersRecyclerView = findViewById(R.id.rv_users);
        this.swipeRefreshLayout = findViewById(R.id.swipeLayout);

        this.usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.usersActivityViewModel.fetchUsers();

        initializeObservers();

        initializeListeners();
    }

    public void startUserDetailActivity(User user) {
        if (user != null) {
            Intent intent = new Intent(this, UserDetailedActivity.class);
            intent.putExtra(Constants.INTENT_DATA_USER, user);

            startActivity(intent);
        }
    }

    private void hideLoadingAndShowData() {
        this.usersRecyclerView.setVisibility(View.VISIBLE);
        this.circularProgressIndicator.setVisibility(View.GONE);
        if (this.swipeRefreshLayout.isRefreshing()) {
            this.swipeRefreshLayout.setRefreshing(Boolean.FALSE);
        }
    }

    private void showLoading() {
        this.usersRecyclerView.setVisibility(View.GONE);
        this.circularProgressIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void initializeListeners() {
        this.swipeRefreshLayout.setOnRefreshListener(() -> {
            this.usersActivityViewModel.fetchUsers();
        });
    }

    @Override
    public void initializeObservers() {
        this.usersActivityViewModel.getUsers().observeForever(listApiResponse -> {
            if (listApiResponse instanceof ApiResponse.Loading) {
                showLoading();
            } else if (listApiResponse instanceof ApiResponse.Success) {
                this.usersRecyclerView.setAdapter(new UserAdapter(listApiResponse.data));
                hideLoadingAndShowData();
            } else if (listApiResponse instanceof ApiResponse.Error) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_donwloading_users), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.usersActivityViewModel = null;
    }
}