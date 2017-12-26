package me.keliu.dormitory_selection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectionNumber extends AppCompatActivity implements View.OnClickListener{

    private TextView button1;
    private TextView button2;
    private TextView button3;
    private TextView button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_num);
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String s = pref.getString("building","");
        Log.d("几号楼", s);
        initView();
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    void initView(){
        button1 = findViewById(R.id.num1);
        button2 = findViewById(R.id.num2);
        button3 = findViewById(R.id.num3);
        button4 = findViewById(R.id.num4);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        switch(v.getId()){
            case R.id.num1:
                editor.putString("number","1");
                editor.commit();
                Log.d("单人选","单人同住");
                Toast.makeText(this,"选择成功", Toast.LENGTH_LONG).show();
                Intent intent0 = new Intent(this, Personal.class);
                startActivity(intent0);
                break;
            case R.id.num2:
                editor.putString("number", "2");
                editor.commit();
                Intent intent = new Intent(SelectionNumber.this, Roommate.class);
                Log.d("双人选","双人同住");
                startActivity(intent);
                break;
            case R.id.num3:
                editor.putString("number", "3");
                editor.commit();
                Intent intent1 = new Intent(SelectionNumber.this, Roommate.class);
                Log.d("三人选","三人同住");
                startActivity(intent1);
                break;
            case R.id.num4:
                editor.putString("number", "4");
                editor.commit();
                Intent intent2 = new Intent(SelectionNumber.this, Roommate.class);
                Log.d("四人选","四人同住");
                startActivity(intent2);
                break;
            default:
                break;

        }
    }
}
