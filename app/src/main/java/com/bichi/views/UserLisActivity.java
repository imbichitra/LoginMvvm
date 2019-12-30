package com.bichi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bichi.R;
import com.bichi.adapter.UserAdapter;
import com.bichi.model.User;
import com.bichi.viewmodels.UserListViewModel;
import com.bichi.viewmodels.UserViewModel;

import java.util.List;

public class UserLisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lis);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final UserAdapter userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);

        UserListViewModel userListViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        userListViewModel.getUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> notes) {
                //update RecyclerView
                userAdapter.submitList(notes);
            }
        });
    }
}
