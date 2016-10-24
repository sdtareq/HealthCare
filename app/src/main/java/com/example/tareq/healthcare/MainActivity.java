package com.example.tareq.healthcare;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;   // track the back pressed button

     android.support.v7.widget.CardView card;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Log - in First
        //Intent intent = new Intent(this, LoginActivity.class);
         Intent intent = new Intent(this, MessageActivity.class);
         startActivity(intent);

        init();
    }

    private void init() {

        card = (CardView) findViewById(R.id.card_view_message);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "it works " , Toast.LENGTH_SHORT).show();
            }
        });
    }


    // adding functionality for exit from the app
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "বাহির হওয়ার জন্য দুইবার ব্যাক বাটন ক্লিক করুন", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);


    }
}
