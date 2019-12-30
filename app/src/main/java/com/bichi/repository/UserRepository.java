package com.bichi.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.bichi.interfaces.UserAuth;
import com.bichi.interfaces.UserInterFace;
import com.bichi.model.Result;
import com.bichi.model.User;
import com.bichi.network.APIError;
import com.bichi.network.ErrorUtils;
import com.bichi.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    public static final String TAG = UserRepository.class.getSimpleName();
    private Context context;

    public UserRepository(Application application) {
        context = application;
    }

    public void authUser(final User user, final UserInterFace userInterFace) {
        Call<Result> call = RetrofitClientInstance.cteateService(UserAuth.class).authUser(user);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if (response.isSuccessful()) {
                    userInterFace.onResult(response.body());
                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody());
                    APIError error = ErrorUtils.parseError(response);
                    switch (response.code()) {
                        case 401:
                            userInterFace.onFail(error.getStatus());
                            break;
                        case 404:
                            userInterFace.onFail("not found");
                            break;
                        case 500:
                            userInterFace.onFail("server broken");
                            break;
                        default:
                            userInterFace.onFail("unknown error");
                            break;
                    }
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                userInterFace.onFail(t.getMessage());
            }
        });
    }

    public MutableLiveData<List<User>> getUserList() {
        final MutableLiveData<List<User>> userList = new MutableLiveData<>();
        Call<List<User>> call = RetrofitClientInstance.cteateService(UserAuth.class).getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: "+response.body());
                    userList.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody());
                    APIError error = ErrorUtils.parseError(response);
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(context, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(context, error.getStatus(), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                userList.setValue(null);
            }
        });
        return userList;
    }
}