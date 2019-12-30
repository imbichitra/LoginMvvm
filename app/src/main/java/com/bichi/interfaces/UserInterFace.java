package com.bichi.interfaces;

import com.bichi.model.Result;
import com.bichi.model.User;

public interface UserInterFace {
    void onResult(Result result);
    void onFail(String error);
}
