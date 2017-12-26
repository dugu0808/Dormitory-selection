package me.keliu.dormitory_selection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.keliu.dormitory_selection.bean.SelectRoom;
import me.keliu.dormitory_selection.util.HttpCallbackListener;
import me.keliu.dormitory_selection.util.HttpUtil;
import me.keliu.dormitory_selection.util.JsonUtil;

public class Roommate extends AppCompatActivity implements View.OnClickListener{

    public static final int SELECTION_RESULT = 1;
    private EditText stuid1, code1, stuid2, code2, stuid3, code3;
    private TextView commitButton;
    private TextView stuidt1, vcodet1, stuidt2, vcodet2, stuidt3, vcodet3;


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SELECTION_RESULT:
                    operateSelectionResult((SelectRoom)(msg.obj));
                    break;
                default:
                    break;
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roommate);
        initView();

        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        Log.d("几人",pref.getString("number",""));

        Integer number = Integer.parseInt(pref.getString("number","0"));
        switch(number) {
            case 2:
                stuid2.setVisibility(View.GONE);
                code2.setVisibility(View.GONE);
                stuid3.setVisibility(View.GONE);
                code3.setVisibility(View.GONE);
                stuidt2.setVisibility(View.INVISIBLE);
                vcodet2.setVisibility(View.INVISIBLE);
                stuidt3.setVisibility(View.INVISIBLE);
                vcodet3.setVisibility(View.INVISIBLE);
            case 3:
                stuid3.setVisibility(View.GONE);
                code3.setVisibility(View.GONE);
                stuidt3.setVisibility(View.INVISIBLE);
                vcodet3.setVisibility(View.INVISIBLE);
        }

        commitButton.setOnClickListener(this);
    }

    void initView() {
        commitButton = findViewById(R.id.submit);
        stuid1 = findViewById(R.id.stu1_id2);
        code1 = findViewById(R.id.stu1_vcode2);
        stuid2 = findViewById(R.id.stu2_id2);
        code2 = findViewById(R.id.stu2_vcode2);
        stuid3 = findViewById(R.id.stu3_id2);
        code3 = findViewById(R.id.stu3_vcode2);
        stuidt1 = findViewById(R.id.stu1_id1);
        vcodet1 = findViewById(R.id.stu1_vcode1);
        stuidt2  = findViewById(R.id.stu2_id1);
        vcodet2 = findViewById(R.id.stu2_vcode1);
        stuidt3 = findViewById(R.id.stu3_id1);
        vcodet3 = findViewById(R.id.stu3_vcode1);
    }

    private void operateSelectionResult(SelectRoom selectionResult){
        if(selectionResult.getErrcode().equals("0")){
            Toast.makeText(this,"选择成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Personal.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"选择失败，请重新选择", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Dormitory.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.submit){
            SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
            Integer number = Integer.parseInt(pref.getString("number",""));
            boolean bool = true;
            EditText[] el = new EditText[]{stuid1, code1, stuid2, code2, stuid3, code3};
            for(int i=0; i<2*number-2; i++){
                if(el[i].getText().toString().equals("")){
                    Toast.makeText(this,"信息不能为空", Toast.LENGTH_LONG).show();
                    bool = false;
                }
            }

            if(bool == true){
                String data = "num="+number+"&stuid="+pref.getString("stuid","");
                for(int i=0; i<2*number-2; i+=2){
                    data = data + "&stu"+i+"id="+el[i].getText().toString()+"&v"+i+"code="+el[i+1].getText().toString();
                }
                data = data + "&buildingNo=" + pref.getString("building","");
                final String postdata = new String(data);
                final String url = "https://api.mysspku.com/index.php/V1/MobileCourse/SelectRoom";
                HttpUtil.postSelection(url, postdata, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        SelectRoom selectionResult = JsonUtil.parseSelectionResult(response);
                        if(selectionResult != null){
                            Message msg = new Message();
                            msg.what = SELECTION_RESULT;
                            msg.obj = selectionResult;
                            mHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        }

    }
}
