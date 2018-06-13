package com.cyberw01f.kalories;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    TextView bmiResult, hUnit, wUnit;
    Button calculateButton;
    Double bmi=0.0,uWeight=0.0, uHeight=0.0;
    EditText userWeight, userHeight;
    Switch switchImperial;
    Boolean sw=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Create greeting
        //Toast.makeText(getApplicationContext(), "Welcome to Kalories!", Toast.LENGTH_SHORT).show();

        // Link all instances to widgets
        bmiResult = (TextView) findViewById(R.id.bmiResult);
        calculateButton = (Button) findViewById(R.id.calcBMI);
        userHeight = (EditText) findViewById(R.id.userHeight);
        userWeight = (EditText) findViewById(R.id.userWeight);
        hUnit = (TextView) findViewById(R.id.heightUnits);
        wUnit = (TextView) findViewById(R.id.weightUnits);
        switchImperial = (Switch) findViewById(R.id.switchImperial);

        switchImperial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hUnit.setText("inches");
                    wUnit.setText("lbs");
                    sw = true;
                } else {
                    hUnit.setText("cms");
                    wUnit.setText("kgs");
                    sw = false;
                }
            }
        });



        calculateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String msg;
                Boolean noInput=false;

                if(userHeight.getText()==null || userHeight.getText().toString().equals("")){
                    uHeight=0.0;
                    noInput=true;
                } else
                    uHeight = Double.parseDouble(userHeight.getText().toString());

                if(userWeight.getText()==null || userWeight.getText().toString().equals("")){
                    uWeight=0.0;
                    noInput=true;
                } else
                    uWeight = Double.parseDouble(userWeight.getText().toString());

                if(sw) {
                    uHeight = uHeight*2.54;
                    uWeight = uWeight/2.2;
                    sw = false;
                }
                bmi = uWeight/uHeight/uHeight*(100.0*100.0);
                double newBmi = Math.round(bmi*100.0)/100.0;


                if (newBmi < 18.5) {
                    msg = "You are Under Weight";
                } else if (newBmi < 24.9) {
                    msg =  "You are Normal Weight";
                } else if (newBmi < 29.9) {
                    msg = "You are Over Weight";
                } else if (newBmi < 40) {
                    msg = "You are Obese";
                } else {
                    msg = "Enter valid values";
                    newBmi=0.0;
                }

                if (noInput){
                    msg = "Missing input";
                    bmiResult.setText(msg);
                } else bmiResult.setText("BMI: " + newBmi + "\n" + msg);
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about){
            Intent i = new Intent(this,aboutMe.class);
            startActivity(i);
        }

        if(id == R.id.action_clear){
            userHeight.setText("");
            userWeight.setText("");
        }

        return super.onOptionsItemSelected(item);
    }
}
