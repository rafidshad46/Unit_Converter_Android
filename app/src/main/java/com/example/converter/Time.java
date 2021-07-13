package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Time extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button back,submit,reset;
    private TextView input,mili,sec,min,h,d,w;
    private Spinner spinner;

    private String value="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        spinner=findViewById(R.id.spinner);
        input=findViewById(R.id.input);
        back=findViewById(R.id.back);
        submit=findViewById(R.id.submit);
        mili=findViewById(R.id.milisecond);
        sec=findViewById(R.id.second);
        min=findViewById(R.id.minute);
        h=findViewById(R.id.hour);
        d=findViewById(R.id.day);
        reset=findViewById(R.id.reset);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(Time.this,R.array.times,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                mili.setText("0");
                sec.setText("0");
                min.setText("0");
                h.setText("0");
                d.setText("0");
            }
        });

        findViewById(R.id.freq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Time.this,Data.class));
            }
        });

        findViewById(R.id.temp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Time.this,Temperature.class));
            }
        });

        findViewById(R.id.length).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Time.this,Length.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        value=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Select a Frequency", Toast.LENGTH_SHORT).show();
    }

    public void backButton(View view) {
        finish();
        startActivity(new Intent(Time.this,MainActivity.class));
    }

    public void submitButton(View view) {
        String s=input.getText().toString().trim();
        if(!s.isEmpty()){
            double v=Double.parseDouble(s);

            if(value.equals("Milisecond")){
                mili.setText(String.valueOf(v));
                double sc=v/1000;
                double mnt=v/60000;
                double hr=v/3600000;
                double dy=v/864000000;

                sec.setText(new DecimalFormat("##.##").format(sc));
                min.setText(new DecimalFormat("##.##").format(mnt));
                h.setText(new DecimalFormat("##.##").format(hr));
                d.setText(new DecimalFormat("##.##").format(dy));
            }
            else if(value.equals("Second")){
                sec.setText(String.valueOf(v));

                double ml=v*1000;
                double mnt=v/60;
                double hr=v/3600;
                double dy=v/86400;

                mili.setText(new DecimalFormat("##.##").format(ml));
                min.setText(new DecimalFormat("##.##").format(mnt));
                h.setText(new DecimalFormat("##.##").format(hr));
                d.setText(new DecimalFormat("##.##").format(dy));
            }
            else if(value.equals("Minute")){
                min.setText(String.valueOf(v));

                double ml=v*60000;
                double sc=v*60;
                double hr=v/60;
                double dy=v/1440;

                mili.setText(new DecimalFormat("##.##").format(ml));
                sec.setText(new DecimalFormat("##.##").format(sc));
                h.setText(new DecimalFormat("##.##").format(hr));
                d.setText(new DecimalFormat("##.##").format(dy));
            }
            else if(value.equals("Hour")){
                h.setText(String.valueOf(v));

                double ml=v*3600000;
                double sc=v*3600;
                double mnt=v*60;
                double dy=v/24;

                mili.setText(new DecimalFormat("##.##").format(ml));
                sec.setText(new DecimalFormat("##.##").format(sc));
                min.setText(new DecimalFormat("##.##").format(mnt));
                d.setText(new DecimalFormat("##.##").format(dy));
            }
            else if(value.equals("Day")){
                d.setText(String.valueOf(v));

                double ml=v*864000000;
                double sc=v*86400;
                double mnt=v*1440;
                double hr=v*24;

                mili.setText(new DecimalFormat("##.##").format(ml));
                sec.setText(new DecimalFormat("##.##").format(sc));
                min.setText(new DecimalFormat("##.##").format(mnt));
                h.setText(new DecimalFormat("##.##").format(h));
            }


        }else{
            Toast.makeText(this, "Please Enter Value", Toast.LENGTH_SHORT).show();
        }
    }
}
