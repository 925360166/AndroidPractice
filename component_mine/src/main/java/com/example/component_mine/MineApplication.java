package com.example.component_mine;

import android.app.Application;

import com.example.common_lib.IAppCompat;
import com.example.common_lib.ServiceFactory;

/**
 * Author: SongWei
 * Date:   2019/12/3
 * Usage:
 */
public class MineApplication extends Application implements IAppCompat {

    @Override
    public void initialize(Application app) {
        ServiceFactory.getInstance().setMineService(new MineService());
    }
}
