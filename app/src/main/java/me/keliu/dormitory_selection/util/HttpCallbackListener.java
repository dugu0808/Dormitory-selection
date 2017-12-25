package me.keliu.dormitory_selection.util;

public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception e);
}
