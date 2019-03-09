package com.example.kalkulatorbmi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button buttonOK;
    protected EditText etMasa;
    public EditText etWzrost;
    public TextView etBMI;
    public ImageView obrazek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOK= (Button) findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(this);
        etMasa= (EditText) findViewById(R.id.etMasa);
        etWzrost= (EditText) findViewById(R.id.etWzrost);
        etBMI=(TextView) findViewById(R.id.etBMI);
        obrazek=(ImageView) findViewById(R.id.obrazek);


    }

    @Override
    public void onClick(View v) {
        String smasa= etMasa.getText().toString().trim();
        String swzrost= etWzrost.getText().toString().trim();
        //String wynik=smasa+swzrost;

        if(smasa.isEmpty() || swzrost.isEmpty()){
            etBMI.setText("Nieprawidłowe dane");
        } else {
            double masa=Double.parseDouble(smasa);
            double wzrost=Double.parseDouble(swzrost);
            double wzrostM=wzrost/100;
            double bmi=masa/wzrostM/wzrostM;
            bmi=round(bmi,2);
            String komunikat="";
            if(bmi<18.49){
                komunikat="Niedowaga!!!";
                obrazek.setImageResource(R.drawable.bad);
            }
            else if(bmi>18.5 && bmi<24.99){
                komunikat="Waga prawidłowa";
                obrazek.setImageResource(R.drawable.ok);
            }
            else if(bmi>25){
                komunikat="Nadwaga!!!";
                obrazek.setImageResource(R.drawable.bad);
            }



            String wynik = String.valueOf(bmi) + "  " + komunikat;
            etBMI.setText(wynik);
        }
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(buttonOK.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
