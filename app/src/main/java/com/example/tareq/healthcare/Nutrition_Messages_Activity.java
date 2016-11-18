package com.example.tareq.healthcare;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Nutrition_Messages_Activity extends AppCompatActivity {
    Button btANC1_nutrition_1, btANC1_nutrition_2;
    LinearLayout linearLayout_container_mother, linearLayout_container_child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_messages);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        init();


    }

    private void init() {
        linearLayout_container_mother= (LinearLayout) findViewById(R.id.LinearLayout_Container_Mother);
        linearLayout_container_child = (LinearLayout) findViewById(R.id.LinearLayout_Container_Child);

       linearLayout_container_mother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Nutrition_Messages_Activity.this);
                //LayoutInflater inflater = getLayoutInflater();
                View messageView = getLayoutInflater().inflate(R.layout.messages_for_mother,null);


              //  builder.setTitle("ANC 1");
                builder.setView(messageView);


                builder.setNegativeButton("বাতিল করুন", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                AlertDialog dialog =   builder.create();
                dialog.show();

            }
        });


        linearLayout_container_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Nutrition_Messages_Activity.this);
                //LayoutInflater inflater = getLayoutInflater();
                View messageView = getLayoutInflater().inflate(R.layout.messages_for_child,null);


                //  builder.setTitle("ANC 1");
                builder.setView(messageView);


                builder.setNegativeButton("বাতিল করুন", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                AlertDialog dialog =   builder.create();
                dialog.show();

            }
        });
    }


}
