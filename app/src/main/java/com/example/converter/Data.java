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

public class Data extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //

    private Button back,submit,reset;
    private TextView input,h,gh,mh,kh;
    private Spinner spinner;

    private String value="";
    private double hertz=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        spinner=findViewById(R.id.spinner);
        input=findViewById(R.id.input);
        back=findViewById(R.id.back);
        submit=findViewById(R.id.submit);
        reset=findViewById(R.id.reset);
        h=findViewById(R.id.hertzshow);
        kh=findViewById(R.id.kilohertzshow);
        mh=findViewById(R.id.megahertzshow);
        gh=findViewById(R.id.gigahertzshow);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(Data.this,R.array.frequencies,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h.setText("0");
                gh.setText("0");
                mh.setText("0");
                kh.setText("0");
                input.setText("");
            }
        });

        findViewById(R.id.time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Data.this,Time.class));
            }
        });

        findViewById(R.id.temp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Data.this,Temperature.class));
            }
        });

        findViewById(R.id.length).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Data.this,Length.class));
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
        startActivity(new Intent(Data.this,MainActivity.class));
    }

    public void submitButton(View view) {
        String s=input.getText().toString().trim();
        if(!s.isEmpty()){
            double d=Double.parseDouble(s);
            if(value.equals("Hertz")){
                hertz=d;
                double kilo=hertz*1000;
                double mega=hertz*1000000;
                double giga=hertz*1000000000;
                h.setText(new DecimalFormat("##.##").format(hertz));
                kh.setText(new DecimalFormat("##.##").format(kilo));
                mh.setText(new DecimalFormat("##.##").format(mega));
                gh.setText(new DecimalFormat("##.##").format(giga));
            }
            else if(value.equals("Kilohertz")){
                hertz=1000;
                double mega=d/hertz;
                double giga=d/1000000 ;
                h.setText(new DecimalFormat("##.##").format(hertz));
                kh.setText(new DecimalFormat("##.##").format(d));
                mh.setText(new DecimalFormat("##.##").format(mega));
                gh.setText(new DecimalFormat("##.##").format(giga));
            }
            else if(value.equals("Megahertz")){
                hertz=1000000;
                double kilo=d*1000;
                double giga=hertz/1000;

                h.setText(new DecimalFormat("##.##").format(hertz));
                kh.setText(new DecimalFormat("##.##").format(kilo));
                mh.setText(new DecimalFormat("##.##").format(d));
                gh.setText(new DecimalFormat("##.##").format(giga));

            }
            else if(value.equals("Gigahertz")){
                hertz=1000000000;
                double kilo=d*1000000;
                double mega=hertz*1000;

                h.setText(new DecimalFormat("##.##").format(hertz));
                kh.setText(new DecimalFormat("##.##").format(kilo));
                mh.setText(new DecimalFormat("##.##").format(mega));
                gh.setText(new DecimalFormat("##.##").format(d));

            }
        }else{
            Toast.makeText(this, "Please Enter Value", Toast.LENGTH_SHORT).show();
        }
    }

}
