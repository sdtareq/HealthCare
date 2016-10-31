package com.example.tareq.healthcare;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;

public class Nutrition_Messages_Activity extends AppCompatActivity {
    Button btANC1_nutrition_1, btANC1_nutrition_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_messages);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        init();


    }

    private void init() {
        btANC1_nutrition_1 = (Button) findViewById(R.id.btANC1_nutrition1);
        btANC1_nutrition_2 = (Button) findViewById(R.id.btANC1_nutrition2);

        btANC1_nutrition_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Nutrition_Messages_Activity.this);

                builder.setTitle("ANC 1");

                builder.setMessage("মায়েদের মেসেজ দিন \n" +
                        "জনকে মেসেজ দেয়া হয়নি \n" +
                        "জনকে মেসেজ দেয়া হয়েছে \n" +
                        "মোট সংখ্যা 00 জন\n" +
                        "\n" +
                        "নতুন মা         রেজিস্টার করুন\n"+
                        "মায়েদের মেসেজ দিন \n" +
                        "জনকে মেসেজ দেয়া হয়নি \n" +
                        "জনকে মেসেজ দেয়া হয়েছে \n" +
                        "মোট সংখ্যা 00 জন\n" +
                        "\n" +
                        "নতুন মা         রেজিস্টার করুন\n"+
                        "মায়েদের মেসেজ দিন \n" +
                        "জনকে মেসেজ দেয়া হয়নি \n" +
                        "জনকে মেসেজ দেয়া হয়েছে \n" +
                        "মোট সংখ্যা 00 জন\n" +
                        "\n" +
                        "নতুন মা        রেজিস্টার করুন\n"+
                        "মায়েদের মেসেজ দিন \n" +
                        "জনকে মেসেজ দেয়া হয়নি \n" +
                        "জনকে মেসেজ দেয়া হয়েছে \n" +
                        "মোট সংখ্যা 00 জন\n" +
                        "\n" +
                        "নতুন মা      রেজিস্টার করুন\n"+
                        "মায়েদের মেসেজ দিন \n" +
                        "জনকে মেসেজ দেয়া হয়নি \n" +
                        "জনকে মেসেজ দেয়া হয়েছে \n" +
                        "মোট সংখ্যা 00 জন\n" +
                        "\n" +

                        "মায়েদের মেসেজ দিন \n" +
                        "জনকে মেসেজ দেয়া হয়নি \n" +
                        "জনকে মেসেজ দেয়া হয়েছে \n" +
                        "মোট সংখ্যা 00 জন\n" +
                        "\n" +
                        "নতুন মা রেজিস্টার করুন\n"+
                        "মায়েদের মেসেজ দিন \n" +
                        "জনকে মেসেজ দেয়া হয়নি \n" +
                        "জনকে মেসেজ দেয়া হয়েছে \n" +
                        "মোট সংখ্যা 00 জন\n" +
                        "\n" +
                        "নতুন মা রেজিস্টার করুন\n");


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
