package com.example.eigo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button tangen_1button = (Button) findViewById(R.id.tangen_1);
   //      TextView answer1 = (TextView)findViewById(R.id.answer_1);
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
            // インテントに保存されたデータを取得
            String data = intent2.getStringExtra("keyword");
   //        answer1.setText(data);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str = prefs.getString("string","");

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("string", "value");
        editor.apply();
    }
}
