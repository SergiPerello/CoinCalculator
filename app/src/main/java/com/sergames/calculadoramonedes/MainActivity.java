package com.sergames.calculadoramonedes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView userInput;
    /*
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = findViewById(R.id.userInput);
        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_CE).setOnClickListener(this);
        findViewById(R.id.btn_remove).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_0: addNumber("0"); break;
            case R.id.btn_1: addNumber("1"); break;
            case R.id.btn_2: addNumber("2"); break;
            case R.id.btn_3: addNumber("3"); break;
            case R.id.btn_4: addNumber("4"); break;
            case R.id.btn_5: addNumber("5"); break;
            case R.id.btn_6: addNumber("6"); break;
            case R.id.btn_7: addNumber("7"); break;
            case R.id.btn_8: addNumber("8"); break;
            case R.id.btn_9: addNumber("9"); break;
            case R.id.btn_dot: addNumber(","); break;
            case R.id.btn_CE: clearInput("0"); break;
        }
    }

    public void addNumber(String number){
        String getUserString = userInput.getText().toString();
        if(getUserString.equals("0")){
            userInput.setText(number);
        }
        else if (number.equals(",")){
            if(getUserString.indexOf(',')==0){//first position
                userInput.setText(0+number);
            }
            else if (getUserString.contains(",")){//second coma
                Toast.makeText(this, "Coma already inserted!", Toast.LENGTH_LONG).show();
            }
            else if (getUserString.substring(getUserString.lastIndexOf(',') + 1).length()>=2){//more than 2 decimals
                Toast.makeText(this, "Can't insert more decimals!", Toast.LENGTH_LONG).show();
            }
            else {
                userInput.setText("0");
            }
        }
        else userInput.setText(getUserString+number);
    }
    public void clearInput(String text){
        userInput.setText(text);
    }
}
