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
       // String message = "recycling is becoming common in";
        //受け取った文章を単語に分割する
       message = message.replaceAll(",", "");
        String[] messagetango = message.split(" ", 0);
        //textView.setText(String.valueOf(messagetango.length));

        // EditTextからテキストを取得
        String mondaibun = "recycling is becoming common in people's daily lives";
        String[] tango = mondaibun.split(" ", -1);
        String answer = "";
        int spanColor = Color.RED;
        if(messagetango.length >= 2) {
            class Levenstein_distance {
                String LevensteinDistance(String array_left, String array_right) {
                    String blank = "-";
                    String  array_left2 = blank + " " + array_left;
                    String  array_right2 = blank+ " " + array_right;
                    String[] left = array_left2.split(" ",0);
                    String[] right = array_right2.split(" ",0);
                    int array_[][] = new int[left.length][right.length];
                    int x = 0;
                    int COST_DEL = 1;
                    int COST_INS = 1;
                    int COST_SUB = 1;
                    int c_del = 0;
                    int c_ins = 0;
                    int c_sub = 0;
                    int c_match = 0;
                    int candidate = 0;
                    int candidate2 = 0;
                    int candidate3 = 0;
                    for (int i = 0; i < left.length; i++) {
                        array_[i][0] = i;
                    }
                    for (int j = 0; j < right.length; j++) {
                        array_[0][j] = j;
                    }
                    for (int i = 1; i < left.length; i++) {
                        for (int j = 1; j < right.length; j++) {
                            c_del = array_[i - 1][j] + COST_DEL;
                            c_ins = array_[i][j - 1] + COST_INS;
                            c_sub = array_[i - 1][j - 1] + COST_SUB;
                            if (left[i].equals(right[j])) {
                                x = 0;
                            } else {
                                x = 1;
                            }
                            c_match = array_[i - 1][j - 1] + x;
                            candidate = Math.min(c_del, c_ins);
                            candidate2 = Math.min(c_match, c_sub);
                            candidate3 = Math.min(candidate, candidate2);
                            array_[i][j] = candidate3;


                        }
                   }

                    int score_left = 0;
                    int score_up = 0;
                    int score_naname = 0;
                    int position_left = left.length-1;
                    int position_right = right.length-1;
                    int score = 0;
                    String seikai = "";
                    for (int i = 0; i < (left.length) * (right.length); i++) {
                        if (position_left == 0 && position_right == 0) {
                            break;
                        } else if (position_left == 0) {
                            score_naname = 10000;
                            score_up = 10000;
                            score_left = array_[position_left][position_right - 1];
                        } else if (position_right == 0) {
                            score_naname = 10000;
                            score_left = 10000;
                            score_up = array_[position_left - 1][position_right];
                        } else {
                            score_left = array_[position_left][position_right - 1];
                            score_up = array_[position_left - 1][position_right];
                            score_naname = array_[position_left - 1][position_right - 1];
                        }
                        score = array_[position_left][position_right];
                        if (score_naname <= score_left && score_naname <= score_up && score >= score_naname) {
                            position_left = position_left - 1;
                            position_right = position_right - 1;
                            if (score > score_naname) {
                          //     seikai += String.valueOf(position_left + 1);
                          //      seikai += " ";
                                seikai += String.valueOf(position_right + 1);
                                seikai += " ";
                            }
                            score = array_[position_left][position_right];
                       } else if (score_left <= score_naname && score_left <= score_up && score > score_left) {
                            position_left = position_left;
                            position_right = position_right - 1;
                            if (score > score_left) {
                         //      seikai += String.valueOf(position_left);
                         //       seikai += " ";
                                seikai += String.valueOf(position_right + 1);
                                seikai += " ";
                            }
                            score = array_[position_left][position_right];
                        } else if (score_up <= score_naname && score_up <= score_left && score > score_up) {
                            position_left = position_left - 1;
                            position_right = position_right;
                            if (score > score_up) {
             //                   seikai += String.valueOf(position_left + 1);
             //                   seikai += " ";
             //                   seikai += String.valueOf(position_right);
             //                   seikai += " ";
                            }
                        } else {

                        }

                    }
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

            textView.setText(message);
            if( distance.length() != 0) {
                SpannableStringBuilder ssb = new SpannableStringBuilder(message);
                String[] distance_right_index = distance.split(" ", 0);
                int place = 10000;
                String red_tango = "";
                int red_index = 10000;
                int red_tango_length = 0;
                int distance_to_red_tango = 0;
                for (int i = 0; i < distance_right_index.length; i++) {
                    place = Integer.parseInt(distance_right_index[i]);
                    red_tango += messagetango[place - 1];
                    for (int j = 0; j < place - 1; j++) {
                        distance_to_red_tango = distance_to_red_tango + messagetango[j].length() + 1;
                    }
                    red_index = message.indexOf(red_tango, distance_to_red_tango);
                    red_tango_length = red_tango.length();
                    ssb.setSpan(new ForegroundColorSpan(spanColor), red_index, red_index + red_tango_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.setText(ssb);
                    red_tango = "";
                    distance_to_red_tango = 0;
                }
            }

        }


        else{
            if(mondaibun.equals(message)){
                textView.setText(message);
            }
            else{
                textView.setText(message);
                SpannableStringBuilder ssb2 = new SpannableStringBuilder(message);
                ssb2.setSpan(new ForegroundColorSpan(spanColor), 0,message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(ssb2);
            }
        }

        TextView textview2 = findViewById(R.id.resultLabel);
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

