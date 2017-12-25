package me.keliu.dormitory_selection.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 45023 on 2017/12/03.
 */

public class NetUtil {
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;

    public static int getNetworkState(Context context){
        ConnectivityManager conManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        if(networkInfo == null){
            return NETWORN_NONE;
        }

        int nType = networkInfo.getType();
        if(nType == ConnectivityManager.TYPE_MOBILE){
            return NETWORN_MOBILE;
        }
        else if(nType == ConnectivityManager.TYPE_WIFI){
            return NETWORN_WIFI;
        }
        return NETWORN_NONE;
    }
}
