package me.keliu.dormitory_selection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.keliu.dormitory_selection.bean.GetRoom;
import me.keliu.dormitory_selection.util.HttpCallbackListener;
import me.keliu.dormitory_selection.util.HttpUtil;
import me.keliu.dormitory_selection.util.JsonUtil;

/**
 * Created by 45023 on 2017/12/25.
 */

public class Dormitory extends AppCompatActivity implements View.OnClickListener {


    private TextView button5;
    private TextView button13;
    private TextView button14;
    private TextView button8;
    private TextView button9;

    private TextView button5_2;
    private TextView button13_2;
    private TextView button14_2;
    private TextView button8_2;
    private TextView button9_2;
    private static final int DORMITORY_INFORMATION = 1;


    //处理子线程返回的个人信息
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case DORMITORY_INFORMATION:
                    updateInformation((GetRoom) (msg.obj));
                    break;
                default:
                    break;
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_room);
        initView();
        button5.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String gender = pref.getString("gender", "");

        //get请求
        final String informationUrl = "https://api.mysspku.com/index.php/V1/MobileCourse/getRoom?gender=" + gender;
        Log.d("userGender", informationUrl);
        queryInformation(informationUrl);
    }

    // 初始化信息
    void initView() {
        button5 = findViewById(R.id.build5_2);
        button13 = findViewById(R.id.build13_2);
        button14 = findViewById(R.id.build14_2);
        button8 = findViewById(R.id.build8_2);
        button9 = findViewById(R.id.build9_2);

        button5_2 = findViewById(R.id.select5);
        button13_2 = findViewById(R.id.select13);
        button14_2 = findViewById(R.id.select14);
        button8_2 = findViewById(R.id.select8);
        button9_2 = findViewById(R.id.select9);
    }

    //更新信息

    void updateInformation(GetRoom dormitoryInformation) {
        button5.setText(dormitoryInformation.getFifthNumber());
        button13.setText(dormitoryInformation.getThirteenthNumber());
        button14.setText(dormitoryInformation.getFourteenthNumber());
        button8.setText(dormitoryInformation.getEighthNumber());
        button9.setText(dormitoryInformation.getNinethNumber());
    }

    //向服务器请求查询并返回信息
    void queryInformation(final String url) {
        HttpUtil.queryFrom(url, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                //将返回结果解析成java对象
                GetRoom dormitoryInformation = JsonUtil.parseDormitoryInformation(response);
                //如果结果不为空，则将子线程的结果发送给主线程处理
                if (dormitoryInformation != null) {
                    Message msg = new Message();
                    msg.what = DORMITORY_INFORMATION;
                    msg.obj = dormitoryInformation;
                    mHandler.sendMessage(msg);
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Dormitory.this, Selection.class);
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        switch (v.getId()) {
            case R.id.select5:
                if(Integer.parseInt(button5.getText().toString()) > 0){
                    editor.putString("building","5");
                    editor.commit();
                    startActivity(intent);
                }
                break;
            case R.id.select13:

                if(Integer.parseInt(button13.getText().toString())>0){
                    editor.putString("building", "13");
                    editor.commit();
                    startActivity(intent);
                }
                break;
            case R.id.select14:
                if(Integer.parseInt(button14.getText().toString()) > 0){
                    editor.putString("building", "14");
                    editor.commit();
                    startActivity(intent);
                }
                break;
            case R.id.select8:
                if(Integer.parseInt(button8.getText().toString()) > 0){
                    editor.putString("building", "8");
                    editor.commit();
                    startActivity(intent);
                }
                break;
            case R.id.select9:
                if(Integer.parseInt(button9.getText().toString()) >0 ){
                    editor.putString("building","9");
                    editor.commit();
                    startActivity(intent);
                }
                break;

        }
    }
}

