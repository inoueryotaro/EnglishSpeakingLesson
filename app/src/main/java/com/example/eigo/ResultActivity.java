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
        final TextView textView = findViewById(R.id.text_view5);
        //データを受け取る
        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //受け取った文章を単語に分割する
        message = message.replaceAll(",","");
        String[] messagetango = message.split(" ", -1);

        // EditTextからテキストを取得
        String mondaibun = "recycling is becoming common in people's daily lives";
        String[] tango = mondaibun.split(" ", -1);


        String answer = "";
        int count = 0;
        int spanColor = Color.RED;
        class Levenstein_distance{
            double LevensteinDistance(String array_left, String array_right) {
                int array_[][] = new int[array_left.length() + 1][array_right.length() + 1];
                int x = 0;

                String[] left = array_left.split("");
                String[] right = array_right.split("");
                int left_point = 0;
                int right_point = 0;
                int left_right = 0;
                int minimum = 0;
                for (int i = 0; i < array_left.length() + 1; i++) {
                    array_[i][0] = i;
                }
                for (int j = 0; j < array_right.length() + 1; j++) {
                    array_[0][j] = j;
                }
                for (int i = 1; i < array_left.length() + 1; i++) {
                    for (int j = 1; j < array_right.length() + 1; j++) {
                        left_point = array_[i - 1][j] + 1;
                        right_point = array_[i][j - 1] + 1;
                        x = (left[i - 1].equals(right[j - 1])) ? 0 : 1;
                        left_right = array_[i - 1][j - 1] + x;
                        int min1 = Math.min(left_point, right_point);
                        minimum = Math.min(min1, left_right);
                        array_[i][j] = minimum;
                    }
                }
                double max_string_num = Math.max(array_left.length(), array_right.length());
                double normalized_LD = array_[array_left.length()][array_right.length()] / max_string_num;
                return normalized_LD;
            }

             }
        String right = "ひつまぶし";//"ゴジラ";
        String left  = "ひまつぶし";//"キングゴジラ";
        Levenstein_distance LD = new Levenstein_distance();
        String  ca = String.valueOf(LD.LevensteinDistance(left,right));
        textView.setText(ca);

        //    for( int i = 0; i < messagetango.length; i++ )
        //    {
        //        for ( int j = 0; j < tango.length; j++)
        //        {
        //            if( messagetango[i].equals(tango[j]))
        //            {
        //                messagetango[i] = "-";
        //                tango[j] = "-";
        //                break;
        //            }
        //        }

        //        answer += messagetango[i];
        //        answer += " ";
        //        count++;
        //    }




           // textView.setText(message);
           // if (answer != "") {
           //   String[] answer_tango = answer.split(" ", -1);
           // SpannableStringBuilder ssb = new SpannableStringBuilder(message);
           // for( int j = 0; j < count; j++) {
           //   if (message.contains(answer_tango[j])) {
           //     int index = message.indexOf(answer_tango[j]);
           //   ssb.setSpan(new ForegroundColorSpan(spanColor), index, index+answer_tango[j].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
           //  textView.setText(ssb);
          //  }
          //  }
         //   }

       // for (int i = 0; i < messagetango.length; i++) {
         //   if (tango[i].equals(messagetango[i])) {

           // } else {
             //   answer += messagetango[i];
              //  answer += " ";
               // count++;
           // }
        //}
       // textView.setText(message);
        if (answer != "") {
            String[] answer_tango = answer.split(" ", -1);
            SpannableStringBuilder ssb = new SpannableStringBuilder(message);
            for( int j = 0; j < count; j++) {
                if (message.contains(answer_tango[j])) {
                    int index = message.indexOf(answer_tango[j]);
                    ssb.setSpan(new ForegroundColorSpan(spanColor), index, index+answer_tango[j].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         //           textView.setText(ssb);
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
