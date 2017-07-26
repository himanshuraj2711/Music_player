package com.example.hpand.mp3player;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       tv=(TextView)findViewById(R.id.textView3);
      new CountDownTimer(3000,1000)
      {

          public void onTick(long milli)
          {
            tv.setText(String.valueOf(milli/1000L));
          }
          public void onFinish(){
              {
                  Intent i = new Intent(Splash.this,MainActivity.class);
                  startActivity(i);
                  finish();
              }
          }
      }.start();

    }
}
