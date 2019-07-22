package com.example.eigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        
        Button startbutton = (Button) findViewById(R.id.start_button);
        startbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                start();
            }

            private void start() {
                Intent intent = new Intent(getApplication(), LevelActivity.class);
                startActivity(intent);
            }
        });

    }



}
