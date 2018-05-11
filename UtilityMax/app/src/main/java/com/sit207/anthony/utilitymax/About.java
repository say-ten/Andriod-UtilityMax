//Anthony Ea 214126186 (aea@deakin.edu.au)
//Assignment 2 - SIT207
//Code for playing video.
//References used:https://www.youtube.com/watch?v=voYDlnfcchs

package com.sit207.anthony.utilitymax;

import android.graphics.PixelFormat;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        final ImageButton playButton = (ImageButton)findViewById(R.id.playButton);
        getWindow().setFormat(PixelFormat.UNKNOWN);

        //Displays the video file after button click
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButton.setVisibility(View.INVISIBLE);

                //Displays the video file
                VideoView screenCast = (VideoView) findViewById(R.id.videoView);
                String uriPath = "android.resource://com.sit207.anthony.utilitymax/" + R.raw.screencast;
                Uri uri = Uri.parse(uriPath);
                screenCast.setVideoURI(uri);
                screenCast.requestFocus();
                screenCast.start();
            }
        });
    }
}
