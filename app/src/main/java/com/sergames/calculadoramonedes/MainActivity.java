package com.sergames.calculadoramonedes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = findViewById(R.id.userInput);

        Button coinBtns[] = new Button[4];
        coinBtns[0]= findViewById(R.id.btn_coin_dollar);
        coinBtns[1]= findViewById(R.id.btn_coin_lliures);
        coinBtns[2]= findViewById(R.id.btn_coin_yen);
        coinBtns[3]= findViewById(R.id.btn_coin_Yuan);

        Button numberBtns[] = new Button[10];
        numberBtns[0] = findViewById(R.id.btn_0);
        numberBtns[1] = findViewById(R.id.btn_1);
        numberBtns[2] = findViewById(R.id.btn_2);
        numberBtns[3] = findViewById(R.id.btn_3);
        numberBtns[4] = findViewById(R.id.btn_4);
        numberBtns[5] = findViewById(R.id.btn_5);
        numberBtns[6] = findViewById(R.id.btn_6);
        numberBtns[7] = findViewById(R.id.btn_7);
        numberBtns[8] = findViewById(R.id.btn_8);
        numberBtns[9] = findViewById(R.id.btn_9);

        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_CE).setOnClickListener(this);
        findViewById(R.id.btn_remove).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);

        for (Button button:coinBtns) {
            button.setOnClickListener(this);
            //button.setBackgroundColor(Color.parseColor("#C8FFCD"));
        }
        for (Button button:numberBtns) {
            button.setOnClickListener(this);
        }
    }
    public float dollarConversionValue;
    public float lliuresConversionValue;
    public float yenConversionValue;
    public float yuanConversionValue;


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
            case R.id.btn_remove: removeOneChar(); break;
            case R.id.btn_equal: calculate(); break;
            case R.id.btn_coin_lliures: askConversionFactor("lliures"); break;
            case R.id.btn_coin_dollar: askConversionFactor("dollar"); break;
            case R.id.btn_coin_yen: askConversionFactor("yen"); break;
            case R.id.btn_coin_Yuan: askConversionFactor("yuan"); break;
        }
    }

    private void calculate() {

    }

    private void removeOneChar() {
        String actualNumber = userInput.getText().toString();
        if (actualNumber.length()==1){
            userInput.setText("0");
        }
        if (actualNumber.length()>1) {
            userInput.setText(actualNumber.substring(0, actualNumber.length() - 1));
        }
    }

    public boolean addNumber(String input){
        String actualNumber = userInput.getText().toString();
        if (actualNumber.contains(",")){
            if (actualNumber.substring(actualNumber.lastIndexOf(',') + 1).length()>=2){//more than 2 decimals
                Toast.makeText(this, "Can't insert more decimals!", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if(actualNumber.equals("0")){
            if(input.equals(",")){
                userInput.setText(actualNumber+input);
            }
            else if(!input.equals("0")){
                userInput.setText(input);
            }
        }
        else if (input.equals(",")){
            if (actualNumber.contains(input)){
                Toast.makeText(this, "Coma already inserted!", Toast.LENGTH_LONG).show();
            }
            else userInput.setText(actualNumber+input);
        }
        else userInput.setText(actualNumber+input);
        return false;
    }
    public void clearInput(String text){
        userInput.setText(text);
    }

    public void askConversionFactor(final String coinName){
        AlertDialog ad;
        ad = new AlertDialog.Builder(this).create();
        ad.setTitle("Conversion factor");
        ad.setMessage("Write the " + coinName + " conversion factor:");

        final EditText edtValue = new EditText(this);
        ad.setView(edtValue);
        ad.setButton(AlertDialog.BUTTON_POSITIVE, "Let's go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (coinName.equals("dollar")){
                    dollarConversionValue = Float.parseFloat(edtValue.getText().toString());
                }
                else if (coinName.equals("lliures")){
                    lliuresConversionValue = Float.parseFloat(edtValue.getText().toString());
                }
                else if (coinName.equals("yen")){
                    yenConversionValue = Float.parseFloat(edtValue.getText().toString());
                }
                else if (coinName.equals("yuan")){
                    yuanConversionValue = Float.parseFloat(edtValue.getText().toString());
                }
            }
        });
        ad.show();
    }
/*
    private void dialogConEditText(final String moneda, final Conversor conversor, final Button btnClicked) {
        AlertDialog ad;

        ad = new AlertDialog.Builder(this).create();
        ad.setTitle("Factor de conversió");
        ad.setMessage("Quin es el factor de conversió del " + moneda);

        // Ahora forzamos que aparezca el editText
        final EditText edtValor = new EditText(this);
        ad.setView(edtValor);

        ad.setButton(AlertDialog.BUTTON_POSITIVE,"Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String conversio;
                float conversioF;
                Button ultimaMoneda;

                ultimaMoneda = conversor.getBtn();
                conversio = edtValor.getText().toString();
                conversioF = Float.parseFloat(conversio);

                if (ultimaMoneda != null) {
                    ultimaMoneda.setBackgroundColor(Color.parseColor("#ABABAB"));
                }

                conversor.setConversio(conversioF);
                conversor.setUltimaMoneda(moneda);
                conversor.setBtn(btnClicked);

                btnClicked.setBackgroundColor(Color.parseColor("#000000"));
            }
        });

        ad.show();

        // el Show es asíncrono.


    }

 */
}
