package me.keliu.dormitory_selection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.keliu.dormitory_selection.bean.GetDetail;
import me.keliu.dormitory_selection.util.HttpCallbackListener;
import me.keliu.dormitory_selection.util.HttpUtil;
import me.keliu.dormitory_selection.util.JsonUtil;

public class Personal extends AppCompatActivity implements View.OnClickListener{

    private TextView studentId;
    private TextView studentName;
    private TextView studentGender;
    private TextView studentVcode;
    private TextView studentRoom;
    private TextView studentBuilding;
    private TextView studentLocation;
    private TextView studentGrade;
    private Button searchDormitory;

    private static final int CHECK_INFORMATION = 1;

    //处理子线程返回的个人信息
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case CHECK_INFORMATION:
                    updateInformation((GetDetail) (msg.obj));
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);
        initView();

        //获取登陆活动传递过来的用户id
        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        String uName = pref.getString("stuid","");
        Log.d("personal", uName);

        searchDormitory.setOnClickListener(this);

        //get请求
        final String informationUrl = "https://api.mysspku.com/index.php/V1/MobileCourse/getDetail?stuid="+uName;
        queryInformation(informationUrl);
    }

    void initView(){
        studentId = findViewById(R.id.stu_id2);
        studentName = findViewById(R.id.stu_name2);
        studentGender = findViewById(R.id.stu_gender2);
        studentVcode = findViewById(R.id.stu_vcode2);
        studentRoom = findViewById(R.id.stu_room2);
        studentBuilding = findViewById(R.id.stu_building2);
        studentLocation = findViewById(R.id.stu_location2);
        studentGrade = findViewById(R.id.stu_grade2);
        searchDormitory = findViewById(R.id.query);
    }

    //向服务器请求查询并返回信息
    void queryInformation(final String url){
        HttpUtil.queryFrom(url, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                //将返回结果解析成java对象
                GetDetail studentInformation = JsonUtil.parseInformationJson(response);
                //如果结果不为空，则将子线程的结果发送给主线程处理
                if(studentInformation != null){
                    Message msg = new Message();
                    msg.what = CHECK_INFORMATION;
                    msg.obj = studentInformation;
                    mHandler.sendMessage(msg);
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    //更新信息
    void updateInformation(GetDetail studentInformation){
        studentId.setText(studentInformation.getStuId());
        studentName.setText(studentInformation.getStuName());
        studentGender.setText(studentInformation.getStuGender());
        studentVcode.setText(studentInformation.getStuVcode());
        studentRoom.setText(studentInformation.getStuRoom());
        studentBuilding.setText(studentInformation.getStuBuilding());
        studentLocation.setText(studentInformation.getStuLocation());
        studentGrade.setText(studentInformation.getStuGrade());
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("name", studentInformation.getStuName());
        if(studentInformation.getStuGender().equals("男")){
            editor.putString("gender", "1");
        }else {
            editor.putString("gender", "2");
        }
        editor.putString("vcode", studentInformation.getStuVcode());
        editor.apply();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.query){
            Log.d("查询","search");
            Intent intent = new Intent(Personal.this, Dormitory.class);
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.personalInformation:
                Intent toPersonal = new Intent(this, Personal.class);
                startActivity(toPersonal);
                break;
            case R.id.back:
                Intent intentBack = new Intent(this, MainActivity.class);
                startActivity(intentBack);
                break;
            default:
        }
        return true;

    }
}

