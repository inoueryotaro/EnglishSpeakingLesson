package com.example.eigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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


    }
}
