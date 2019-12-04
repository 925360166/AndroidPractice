package com.example.common_lib;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Author: SongWei
 * Date:   2019/12/4
 * Usage:
 */
public class EmptyLoginService implements ILoginService {
    @Override
    public void launchLoginActivity(Context context) {

    }

    @Override
    public Fragment getFragment(FragmentManager fragmentManager, int viewId, Bundle bundle) {
        return null;//返回一个空的布局
    }
}
