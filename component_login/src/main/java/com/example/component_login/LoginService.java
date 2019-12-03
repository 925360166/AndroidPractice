package com.example.component_login;

import android.content.Context;
import android.content.Intent;

import com.example.common_lib.ILoginService;

/**
 * Author: SongWei
 * Date:   2019/11/28
 * Usage:
 */
public class LoginService implements ILoginService {

    @Override
    public void launchLoginActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
