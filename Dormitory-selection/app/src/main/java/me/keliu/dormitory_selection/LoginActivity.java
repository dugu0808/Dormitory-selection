package me.keliu.dormitory_selection;

import android.content.Context;
import android.graphics.RadialGradient;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();

        createLogo();
        createToast(R.id.tvNext, "Next");
        createToast(R.id.tvSignup, "Sign Up");
        createToast(R.id.tvPolicy, "Privacy Policy");
        createToast(R.id.tvForgot, "Forgot Password, huh?");
        createLoginEvent();
    }

    private void createLogo(){
        Typeface vibeFont = Typeface.createFromAsset(getAssets(), "fonts/GreatVibes-Regular.otf");
        TextView tvLogo = (TextView) findViewById(R.id.tvLogo);
        tvLogo.setTypeface(vibeFont);
    }

    private void createToast(int viewId, final String text){
        TextView view = (TextView) findViewById(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createLoginEvent(){
        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    Toast.makeText(context, "Logging in..", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
