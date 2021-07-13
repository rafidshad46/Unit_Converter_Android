package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button freq,temp,length,time;
    private Animation blink, LtoR, RtoL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        freq=findViewById(R.id.freq);
        temp=findViewById(R.id.temp);
        length=findViewById(R.id.length);
        time=findViewById(R.id.time);

        LtoR= AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
        RtoL= AnimationUtils.loadAnimation(MainActivity.this,R.anim.righttoleft);

        freq.startAnimation(LtoR);
        time.startAnimation(RtoL);
        temp.startAnimation(LtoR);
        length.startAnimation(RtoL);

    }

    public void freqActivity(View v) {
        Animation a= AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
        freq.startAnimation(a);
        startActivity(new Intent(MainActivity.this,Data.class));
    }

    public void timeActivity(View i) {
        Animation b= AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
        time.startAnimation(b);
        startActivity(new Intent(MainActivity.this,Time.class));
    }

    public void tempActivity(View e) {
        Animation c= AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
        temp.startAnimation(c);
        startActivity(new Intent(MainActivity.this,Temperature.class));
    }

    public void lengthActivity(View w) {
        Animation d= AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
        length.startAnimation(d);
        startActivity(new Intent(MainActivity.this,Length.class));
    }
}
