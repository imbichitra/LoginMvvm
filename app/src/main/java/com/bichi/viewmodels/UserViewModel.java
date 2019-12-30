package com.bichi.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bichi.interfaces.UserInterFace;
import com.bichi.model.Result;
import com.bichi.model.User;
import com.bichi.repository.UserRepository;
import com.bichi.views.UserLisActivity;

public class UserViewModel extends AndroidViewModel {
    private User user;
    public String email;
    public String password;
    private UserRepository userRepository;

    private Context context;
    public UserViewModel(@NonNull Application application) {
        super(application);
        context = application;

        userRepository = new UserRepository(application);
        user = new User("","");
    }

    public void onSubmitClick(){
        user.setEmail(email);
        user.setPassword(password);

        userRepository.authUser(user, new UserInterFace() {
            @Override
            public void onResult(Result result) {
                context.startActivity(new Intent(context, UserLisActivity.class));
                Toast.makeText(context, result.getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
