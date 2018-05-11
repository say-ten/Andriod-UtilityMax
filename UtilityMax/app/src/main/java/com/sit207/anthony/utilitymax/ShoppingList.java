//Anthony Ea 214126186 (aea@deakin.edu.au)
//Assignment 2 - SIT207
//Code for shopping listview and buttons to sqlite database.
//References used: pratical videos and my evernotes.

package com.sit207.anthony.utilitymax;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends ListActivity {

    private final static String DATABASE_NAME = "SQLiteDatabase.db";
    private final static int VERSION_NUMBER = 1;
    EditText amount;
    EditText item;
    Button enterButton;
    ShoppingListOpenHelper OpenHelper;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        //Linking the widget ids with names.
        amount = (EditText) findViewById(R.id.editText3);
        item = (EditText) findViewById(R.id.editText4);
        enterButton = (Button) findViewById(R.id.buttonEnter);
        clearButton = (Button) findViewById(R.id.buttonClear);

        OpenHelper = new ShoppingListOpenHelper(this, DATABASE_NAME, null, VERSION_NUMBER);

        displayDataInTable();

        //When enter is pressed the values will be inserted into database.
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputAmount = amount.getText().toString();
                String inputItem = item.getText().toString();
                SQLiteDatabase db = OpenHelper.getWritableDatabase();

                //Wrap database data
                ContentValues values = new ContentValues();
                values.put("item", inputItem);
                int intAmount = Integer.parseInt(inputAmount);
                values.put("amount", intAmount);

                db.insert(ShoppingListOpenHelper.TABLE_NAME, null, values);
                displayDataInTable();
            }
        });
        //When clear is pressed will delete database table.
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = OpenHelper.getWritableDatabase();
                db.delete(ShoppingListOpenHelper.TABLE_NAME, null, null);
                displayDataInTable();
            }
        });
    }

    void displayDataInTable() {
        List<String> values = queryTable();

        if (values != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShoppingList.this, android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);
        }
    }

    List<String> queryTable() {
        List<String> product = new ArrayList<String>();
        SQLiteDatabase db = OpenHelper.getReadableDatabase();
        Cursor cursor = db.query(ShoppingListOpenHelper.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String item = cursor.getString(cursor.getColumnIndex("item"));
            int amount = cursor.getInt(cursor.getColumnIndex("amount"));
            product.add(amount + "x of " + item);
        }
        return product;
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.flashlight, menu);
        return true;
    }

    // Handle action bar item clicks here.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_button) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}