//Anthony Ea 214126186 (aea@deakin.edu.au)
//Assignment 2 - SIT207
//Code for buttons used as well as getting flashlight to turn on/off.
//References used: http://www.theappguruz.com/blog/flash-light-demo

package com.sit207.anthony.utilitymax;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

public class Flashlight extends AppCompatActivity {

    Switch btnSwitch;
    Parameters params;
    MediaPlayer mp;
    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);

        // flash switch button
        btnSwitch = (Switch) findViewById(R.id.switchBtn);

        // Checks to see if the device supports flash.
        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!hasFlash) {
            // If the device cant support flash, shows alert message and closes activity.
            AlertDialog alert = new AlertDialog.Builder(Flashlight.this)
                    .create();
            alert.setTitle("Error Message");
            alert.setMessage("Your device does not support flash light!");
            alert.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Close
                    finish();
                }
            });
            alert.show();
            return;
        }

        // Calls for the camera.
        getCamera();

        // Button to toggle flashlight on and off.
        btnSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isFlashOn) {
                    turnOffFlash();
                } else {
                    turnOnFlash();
                }
            }
        });
    }

    // Calls for the camera.
    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {
                Log.e("Camera Error: ", e.getMessage());
            }
        }
    }


    // Turn On flash.
    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;
        }
    }


    // Turn Off flash.
    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        turnOffFlash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasFlash)
            turnOffFlash();
    }

    //Get camera when activity starts.
    @Override
    protected void onStart() {
        super.onStart();
        getCamera();
    }

    //Releases camera when activity stopped.
    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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