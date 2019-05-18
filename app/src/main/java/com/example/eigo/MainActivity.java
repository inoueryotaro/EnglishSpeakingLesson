package com.example.eigo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1000;
    private TextView textView;

    private int lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lang = 0;

        textView = (TextView)findViewById(R.id.text_view);

        Button buttonStart = (Button)findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                speech();
            }
        });
    }
    private void speech(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        if(lang == 0) {
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH.toString());

        }


        else{
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        }

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,100);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speech!");

        try {

            startActivityForResult(intent, REQUEST_CODE);

        }
        catch (ActivityNotFoundException e){
            e.printStackTrace();
            textView.setText(R.string.error);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // 認識結果を ArrayList で取得
            ArrayList<String> candidates =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if(candidates.size() > 0) {
                // 認識結果候補で一番有力なものを表示
                textView.setText( candidates.get(0));
            }
        }
    }
}
