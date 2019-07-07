package com.example.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView resultField_tv;
    private TextView operation_tv;
    private EditText numberField_et;
    private String lastOperation = "=";
    private Double result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultField_tv = findViewById(R.id.resultField_tv);
        operation_tv = findViewById(R.id.operation_tv);
        numberField_et = findViewById(R.id.numberField_et);
    }

    public void onNumberClick(View v) {
        Button button = (Button) v;
        numberField_et.append(button.getText().toString());
        if (lastOperation.equals("=") && result != null) {
            result = null;
        }
    }

    public void onOperationClick(View v) {
        Button button = (Button) v;
        String operation = button.getText().toString();
        String number = numberField_et.getText().toString();
        if (number.length() > 0) {
            getResult(Double.valueOf(number), operation);
        }
        lastOperation = operation;
        operation_tv.setText(lastOperation);
    }

    private void getResult(Double number, String operation) {
        if(result==null && lastOperation.equals("")){
            result=0.0;
            lastOperation=operation;
        }
        else if (result == null) {
            result = number;
        } else if (lastOperation.equals("=")) {
            lastOperation = operation;
        }
        switch (lastOperation) {
            case "+":
                result += number;
                break;
            case "-":
                result -= number;
                break;
            case "*":
                result *= number;
                break;
            case "/":
                if (number == 0) {
                    result = 0.0;
                } else {
                    result /= number;
                }
                break;}
        resultField_tv.setText(String.valueOf(result));
        numberField_et.setText("");

    }
    public void onClearClick (View v){
        resultField_tv.setText("");
        numberField_et.setText("");
        operation_tv.setText("");
        result=0.0;
    }

}


