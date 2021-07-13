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

public class Length extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button back,submit;
    private TextView input,kilo,meter,centi,foot,inch;
    private Spinner spinner;

    private String value="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        spinner=findViewById(R.id.spinner);
        input=findViewById(R.id.input);
        back=findViewById(R.id.back);
        submit=findViewById(R.id.submit);
        kilo=findViewById(R.id.kilometer);
        meter=findViewById(R.id.meter);
        centi=findViewById(R.id.centimeter);
        foot=findViewById(R.id.foot);
        inch=findViewById(R.id.inch);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(Length.this,R.array.lengths,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                kilo.setText("0");
                meter.setText("0");
                centi.setText("0");
                foot.setText("0");
                inch.setText("0");
            }
        });

        findViewById(R.id.time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Length.this,Time.class));
            }
        });

        findViewById(R.id.temp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Length.this,Temperature.class));
            }
        });

        findViewById(R.id.freq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Length.this,Data.class));
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
        startActivity(new Intent(Length.this,MainActivity.class));
    }

    public void submitButton(View view) {
        String s=input.getText().toString().trim();
        if(!s.isEmpty()){
            double v=Double.parseDouble(s);

            if(value.equals("Kilometer")){
                kilo.setText(s);

                double mtr=v/1000;
                double cnt=v/1e+6;
                double ft=v/2.628e+15;
                double in=v/3.154e+16;

                meter.setText(new DecimalFormat("##.##").format(mtr));
                centi.setText(new DecimalFormat("##.##").format(cnt));
                foot.setText(new DecimalFormat("##.##").format(ft));
                inch.setText(new DecimalFormat("##.##").format(in));

            }
            else if(value.equals("Meter")){
                meter.setText(s);

                double kl=v/1000;
                double cnt=v*100;
                double ft=v*3.281;
                double in=v*39.37;


                kilo.setText(new DecimalFormat("##.##").format(kl));
                centi.setText(new DecimalFormat("##.##").format(cnt));
                foot.setText(new DecimalFormat("##.##").format(ft));
                inch.setText(new DecimalFormat("##.##").format(in));
            }
            else if(value.equals("Centimeter")){
                centi.setText(s);

                double kl=v/100000;
                double mtr=v/100;
                double ft=v/30.48;
                double in=v/2.54;


                kilo.setText(new DecimalFormat("##.##").format(kl));
                meter.setText(new DecimalFormat("##.##").format(mtr));
                foot.setText(new DecimalFormat("##.##").format(ft));
                inch.setText(new DecimalFormat("##.##").format(in));
            }
            else if(value.equals("Foot")){
                foot.setText(s);

                double kl=v/3281;
                double mtr=v/3.281;
                double cnt=v*30.48;
                double in=v*12;

                kilo.setText(new DecimalFormat("##.##").format(kl));
                meter.setText(new DecimalFormat("##.##").format(mtr));
                centi.setText(new DecimalFormat("##.##").format(cnt));
                inch.setText(new DecimalFormat("##.##").format(in));
            }
            else if(value.equals("Inch")){
                inch.setText(s);

                double kl=v/39370;
                double mtr=v/39.37;
                double cnt=v*2.54;
                double ft=v/12;

                kilo.setText(new DecimalFormat("##.##").format(kl));
                meter.setText(new DecimalFormat("##.##").format(mtr));
                centi.setText(new DecimalFormat("##.##").format(cnt));
                foot.setText(new DecimalFormat("##.##").format(ft));
            }


        }else{
            Toast.makeText(this, "Please Enter Value", Toast.LENGTH_SHORT).show();
        }
    }
}
