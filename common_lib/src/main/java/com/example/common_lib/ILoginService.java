package com.example.common_lib;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Author: SongWei
 * Date:   2019/11/21
 * Usage:
 */
public interface ILoginService {

    public void launchLoginActivity(Context context);

    public Fragment getFragment(FragmentManager fragmentManager, int viewId, Bundle bundle);


}
