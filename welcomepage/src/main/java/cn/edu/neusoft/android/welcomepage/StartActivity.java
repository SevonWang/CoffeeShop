package cn.edu.neusoft.android.welcomepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("config", MODE_PRIVATE);

        String time = prefs.getString("time", "");

        if (time.equals("second")) {
            Intent second = new Intent
                    (StartActivity.this, MainActivity.class);
            startActivity(second);
            this.finish();
        } else {
            SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("time", "second");
            editor.commit();
            Intent first = new Intent
                    (StartActivity.this, WelcomeActivity.class);
            startActivity(first);
            this.finish();
        }
    }
}
