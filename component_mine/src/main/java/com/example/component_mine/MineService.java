package com.example.component_mine;

import android.content.Context;
import android.content.Intent;

import com.example.common_lib.IMineService;

/**
 * Author: SongWei
 * Date:   2019/11/28
 * Usage:
 */
public class MineService implements IMineService {

    @Override
    public void launchMineActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MineActivity.class);
        context.startActivity(intent);
    }
}
