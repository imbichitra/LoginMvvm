package com.bichi.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bichi.model.User;
import com.bichi.repository.UserRepository;

import java.util.List;

public class UserListViewModel extends AndroidViewModel {
    private LiveData<List<User>> userList;
    private UserRepository userRepository;
    public UserListViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
        userList = userRepository.getUserList();
    }

    public LiveData<List<User>> getUserList(){
        return userList;
    }
}
