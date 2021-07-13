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
import java.time.temporal.TemporalAccessor;

public class Temperature extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button back,submit,reset;
    private TextView input,cel,far,kel;
    private Spinner spinner;

    private String value="";

    private boolean c=false,f=false,k=false;
    private double celsius=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        spinner=findViewById(R.id.spinner);
        input=findViewById(R.id.input);
        back=findViewById(R.id.back);
        submit=findViewById(R.id.submit);
        reset=findViewById(R.id.reset);
        cel=findViewById(R.id.celsiusshow);
        far=findViewById(R.id.fahrenheitshow);
        kel=findViewById(R.id.kelvinshow);

        cel.setText("0");
        far.setText("0");
        kel.setText("0");

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(Temperature.this,R.array.temps,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cel.setText("0");
                far.setText("0");
                kel.setText("0");
                input.setText("");
            }
        });

        findViewById(R.id.time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Temperature.this,Time.class));
            }
        });

        findViewById(R.id.freq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Temperature.this,Data.class));
            }
        });

        findViewById(R.id.length).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Temperature.this,Length.class));
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
        startActivity(new Intent(Temperature.this,MainActivity.class));
    }

    public void submitButton(View view) {
        String s=input.getText().toString().trim();
        if(!s.isEmpty()){
            if(value.equals("Celsius")){
                cel.setText(s);
                far.setText(new DecimalFormat("##.##").format(CtoF(Double.parseDouble(s))));
                kel.setText(new DecimalFormat("##.##").format(CtoK(Double.parseDouble(s))));
            }
            else if(value.equals("Fahrenheit")){
                far.setText(s);
                cel.setText(new DecimalFormat("##.##").format(FtoC(Double.parseDouble(s))));
                kel.setText(new DecimalFormat("##.##").format(FtoK(Double.parseDouble(s))));
            }
            else if(value.equals("Kelvin")){
                kel.setText(s);
                cel.setText(new DecimalFormat("##.##").format(KtoC(Double.parseDouble(s))));
                far.setText(new DecimalFormat("##.##").format(KtoF(Double.parseDouble(s))));
            }

        }else{
            Toast.makeText(this, "Please Enter Value", Toast.LENGTH_SHORT).show();
        }
    }

    public double CtoF(double d){
        return ((d*9)/5)+32;
    }

    public double FtoC(double d){
        return (((d-32)*5)/9);
    }

    public double CtoK(double d){
        return (d+273.15);
    }

    public double KtoC(double d){
        return (d-273.15);
    }

    public double FtoK(double d){
        return (((d-32)*5)/9)+273.15;
    }

    public double KtoF(double d){
        return (((d-273.15)*9)/5)+32;
    }
}
