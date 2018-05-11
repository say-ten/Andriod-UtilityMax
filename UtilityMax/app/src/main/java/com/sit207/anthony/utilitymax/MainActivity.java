//Anthony Ea 214126186 (aea@deakin.edu.au)
//Assignment 2 - SIT207
//Code for buttons used on the main menu.
//References used:

package com.sit207.anthony.utilitymax;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On clicking quit, closes app and ends process.
        Button Quit;
        Quit = (Button) findViewById(R.id.exitBtn);
        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }

    //Starts the Shopping list activity.
    public void GoToShoppingList(View view) {
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
    }

    //Starts the Calculator activity.
    public void GoToCalculator(View view) {
        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);
    }

    //Starts the Flashlight activity.
    public void GoToFlashlight(View view) {
        Intent intent = new Intent(this, Flashlight.class);
        startActivity(intent);
    }

    //Generates a alert dialog to pick difficulty for game.
    public void GoToPinball(View view) {
        generateAlertDialog();
    }

    public void generateAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Difficulty")
                .setItems(R.array.difficulty_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String[] difficulty_array = getResources().getStringArray(R.array.difficulty_array);
                        Toast.makeText(MainActivity.this, difficulty_array[which], Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Pinball.class);
                        Bundle LevelData = new Bundle();
                        switch (which) {
                            case 0:
                                LevelData.putInt("ySpeed", 80);
                                LevelData.putInt("RACKET_WIDTH", 300);
                                intent.putExtras(LevelData);
                                startActivity(intent);
                                break;
                            case 1:
                                LevelData.putInt("ySpeed", 100);
                                LevelData.putInt("RACKET_WIDTH", 200);
                                intent.putExtras(LevelData);
                                startActivity(intent);
                                break;
                            case 2:
                                LevelData.putInt("ySpeed", 120);
                                LevelData.putInt("RACKET_WIDTH", 100);
                                intent.putExtras(LevelData);
                                startActivity(intent);
                                break;
                        }
                    }

                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Starts the About activity.
    public void GoToAbout(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    //Opens a external browser with google as the website.
    public void GoToGoogle(View view) {
        Uri uri = Uri.parse("http://www.google.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
