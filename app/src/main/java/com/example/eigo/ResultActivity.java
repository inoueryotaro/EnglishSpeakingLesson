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
        //String message = intent1.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String message = "applet is god";
        //受け取った文章を単語に分割する
        message = message.replaceAll(",", "");
        String[] messagetango = message.split(" ", -1);

        // EditTextからテキストを取得
        String mondaibun = "apple is good";
        String[] tango = mondaibun.split(" ", -1);
        String answer = "";
        int count = 0;
        int spanColor = Color.RED;
        class Levenstein_distance {
            String LevensteinDistance(String array_left, String array_right) {
                int array_[][] = new int[array_left.length() + 1][array_right.length() + 1];
                int x = 0;
                String inoue = "";
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
                //String value = String.valueOf(array_[-1][0]);
                //textView.setText(value);
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

                int score_left = 0;
                int score_up = 0;
                int score_naname = 0;
                int position_left = array_left.length();
                int position_right = array_right.length();
                int score = array_[position_left][position_right];
                int j = 0;
                String seikai = "";
                //int[] seikai;
                //seikai = new int[position_right];
                for (int i = 1; i < array_left.length() * array_right.length(); i++) {
                    if (position_left == 0 || position_right == 0) {
                        break;
                    }
                    score_left = array_[position_left][position_right - 1];
                    score_up = array_[position_left - 1][position_right];
                    score_naname = array_[position_left - 1][position_right - 1];
                    if (score_up <= score_naname && score_up <= score_left && score > score_up) {
                        position_left = position_left - 1;
                        position_right = position_right;
                        if (score > score_up) {
                            seikai += right[position_right - 1];
                        }
                        score = array_[position_left][position_right];
                    } else if (score_left <= score_naname && score_left <= score_up && score > score_left) {
                        position_left = position_left;
                        position_right = position_right - 1;
                        if (score > score_left) {
                            seikai += right[position_right];
                        }
                        score = array_[position_left][position_right];
                    } else if (score_naname <= score_left && score_naname <= score_up && score >= score_naname) {
                        position_left = position_left - 1;
                        position_right = position_right - 1;
                        if (score > score_naname) {
                            seikai += right[position_right];
                        }
                        score = array_[position_left][position_right];
                    } else {

                    }

                }
                double max_string_num = Math.max(array_left.length(), array_right.length());
                double normalized_LD = array_[array_left.length()][array_right.length()];
                return seikai;
            }

        }
        String right = message;//ゴジラ";
        String left = mondaibun;//"キングゴジラ";
        String right2;
        // String right = "";
        // String left = "";
        Levenstein_distance LD = new Levenstein_distance();
        String distance = LD.LevensteinDistance(left, right);
        //textView.setText(distance);

        //String  ca = String.valueOf(LD.LevensteinDistance(left,right));
        //textView.setText(ca);
        //right2 = right.replace("Temple","");

        //String ca2 = String.valueOf(LD.LevensteinDistance(left,right2));
        // for (int i = 0; i < messagetango[i].length(); i++){
        //    right2 = right.replace(messagetango[i],"");
        //   double distance2 = LD.LevensteinDistance(left,right2);
        //    if( distance == distance2 - messagetango[i].length()){

        //   }
        //   else if( ){

        // }
        // else{
        //    answer += messagetango[i];
        //    answer += " ";
        //    count++;
        //   textView.setText(answer);
        // }
        // }


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

        String[] distance_tango = distance.split("", -1);
        textView.setText(message);
        //if (answer != "") {
      //  String[] distance_tango = distance.split("", -1);
      SpannableStringBuilder ssb = new SpannableStringBuilder(message);
        for (int j = 1; j < distance_tango.length; j++) {
            if (message.contains(distance_tango[j])) {
                int index = message.indexOf(distance_tango[j]);
                if( index != 0) {
                    ssb.setSpan(new ForegroundColorSpan(spanColor), index, index + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.setText(ssb);
                }
            }
        }


            TextView textview2 = findViewById(R.id.resultLabel);
            //textview2.setText(ca2);
            //if (mondaibun.equals(message)) {
            //   textview2.setText("正解です!");
            //} else {
            //   textview2.setText("不正解です！");
            //}

            Button button = findViewById(R.id.back_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


        }

    }

