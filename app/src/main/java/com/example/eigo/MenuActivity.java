package com.example.eigo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageView iv = (ImageView)this.findViewById(R.id.tangen_1_checkView);
        ImageView iv2 = (ImageView)this.findViewById(R.id.tangen_2_checkView);
        ImageView iv3 = (ImageView)this.findViewById(R.id.tangen_3_checkView);
        ImageView iv4 = (ImageView)this.findViewById(R.id.tangen_4_checkView);
        ImageView iv5 = (ImageView)this.findViewById(R.id.tangen_5_checkView);
        ImageView iv6 = (ImageView)this.findViewById(R.id.tangen_6_checkView);
        ImageView iv7 = (ImageView)this.findViewById(R.id.tangen_7_checkView);
        ImageView iv8 = (ImageView)this.findViewById(R.id.tangen_8_checkView);
        ImageView iv9 = (ImageView)this.findViewById(R.id.tangen_9_checkView);
        ImageView iv10 = (ImageView)this.findViewById(R.id.tangen_10_checkView);
        ImageView iv11 = (ImageView)this.findViewById(R.id.tangen_11_checkView);
        ImageView iv12 = (ImageView)this.findViewById(R.id.tangen_12_checkView);


        Button tangen_1button = (Button) findViewById(R.id.tangen_1);
        tangen_1button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                start();
            }

            private void start() {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
                 Intent intent2 = getIntent();
            String data = intent2.getStringExtra("keyword");

            int sign = 0;
            if( sign == 0 ){
                iv.setImageResource(R.drawable.check_picture);
            }
            else{
                iv.setImageDrawable(null);
            }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str = prefs.getString("string","");

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("string", "value");
        editor.apply();
    }
}
