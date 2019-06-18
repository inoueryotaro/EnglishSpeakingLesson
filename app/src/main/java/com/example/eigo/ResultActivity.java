package com.example.eigo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class ResultActivity extends AppCompatActivity {

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button button2 = findViewById(R.id.yomiage_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplication(), ResultActivity2.class);
                startActivity(intent2);
            }
        });
        //データを受け取る
        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //受け取った文章を単語に分割する
        String[] messagetango = message.split(" ", -1);

        // EditTextからテキストを取得
        String mondaibun = "recycling is becoming common in people's daily lives";
        String[] tango = mondaibun.split(" ", -1);

        TextView textView = findViewById(R.id.text_view5);
        String answer = "";
        int count = 0;
        int spanColor = Color.RED;
        for (int i = 0; i < messagetango.length; i++) {
            if (tango[i].equals(messagetango[i])) {

            } else {
                answer += messagetango[i];
                answer += " ";
                count++;
            }
        }
        textView.setText(message);
        if (answer != "") {
            String[] answer_tango = answer.split(" ", -1);
            SpannableStringBuilder ssb = new SpannableStringBuilder(message);
            for( int j = 0; j < count; j++) {
                if (message.contains(answer_tango[j])) {
                    int index = message.indexOf(answer_tango[j]);
                    ssb.setSpan(new ForegroundColorSpan(spanColor), index, index+answer_tango[j].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.setText(ssb);
                }
            }
        }

            TextView textview2 = findViewById(R.id.resultLabel);
            //textview2.setText(message);
           if (mondaibun.equals(message)) {
                textview2.setText("正解です!");
            } else {
                textview2.setText("不正解です！");
            }

            Button button = findViewById(R.id.back_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


        }
    }
