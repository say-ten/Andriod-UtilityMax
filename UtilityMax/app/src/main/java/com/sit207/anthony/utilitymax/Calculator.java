//Anthony Ea 214126186 (aea@deakin.edu.au)
//Assignment 2 - SIT207
//Code for calculator buttons and functions.
//References used: praticals and my evernotes.

package com.sit207.anthony.utilitymax;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends AppCompatActivity {

    EditText txtFirstValue;
    EditText txtSecondValue;
    EditText txtResultValue;
    Button btnCalculateAdd;
    Button btnCalculateMinus;
    Button btnCalculateDivide;
    Button btnCalculateMultiply;
    Button btnClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //Linking the widget ids with names.
        txtFirstValue = (EditText) findViewById(R.id.editText);
        txtSecondValue = (EditText) findViewById(R.id.editText2);
        txtResultValue = (EditText) findViewById(R.id.editText3);
        btnCalculateAdd = (Button) findViewById(R.id.buttonAdd);
        btnCalculateMinus = (Button) findViewById(R.id.buttonMinus);
        btnCalculateDivide = (Button) findViewById(R.id.buttonDivide);
        btnCalculateMultiply = (Button) findViewById(R.id.buttonMultiply);
        btnClear = (Button) findViewById(R.id.buttonClear);

        // Addition button added to calculate first+second edit texts.
        btnCalculateAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int first = Integer.parseInt(txtFirstValue.getText().toString());
                final int second = Integer.parseInt(txtSecondValue.getText().toString());
                final Integer result = first + second;
                txtResultValue.setText(result.toString());
            }
        });
        // Minus button added to calculate first-second edit texts.
        btnCalculateMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int first = Integer.parseInt(txtFirstValue.getText().toString());
                final int second = Integer.parseInt(txtSecondValue.getText().toString());
                final Integer result = first - second;
                txtResultValue.setText(result.toString());
            }
        });
        // Divide button added to calculate first/second edit texts.
        btnCalculateDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int first = Integer.parseInt(txtFirstValue.getText().toString());
                final int second = Integer.parseInt(txtSecondValue.getText().toString());
                final Integer result = first / second;
                txtResultValue.setText(result.toString());
            }
        });
        // Multiply button added to calculate first*second edittexts.
        btnCalculateMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int first = Integer.parseInt(txtFirstValue.getText().toString());
                final int second = Integer.parseInt(txtSecondValue.getText().toString());
                final Integer result = first * second;
                txtResultValue.setText(result.toString());
            }
        });
        //when clear is pressed all edit texts will become empty.
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtFirstValue.setText("");
                txtSecondValue.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar clickable items here.
        int id = item.getItemId();
        if (id == R.id.menu_button) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.clear_button) {
            txtFirstValue.setText("");
            txtSecondValue.setText("");
        }
        return super.onOptionsItemSelected(item);
    }

}