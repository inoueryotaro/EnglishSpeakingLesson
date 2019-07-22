package com.example.eigo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class KenshoResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kensho_result);

        final TextView textView = findViewById(R.id.text);
        TextView textView2 = findViewById(R.id.text2);

        Intent intent = getIntent();
        String data = intent.getStringExtra("kenshomondai");
        String data2 = intent.getStringExtra("kenshoresult");

        //textView.setText(data);
        //textView2.setText(data2);
        class Levenstein_distance {
            String LevensteinDistance(String array_left, String array_right) {

                String[] left = array_left.split("",0);
                String[] right = array_right.split("",0);
                //textView.setText(left[1]);
                // 結果はaでした。
                //textView.setText(left[6]);
                //textView.setText(String.valueOf(left.length));
                //結果は6でした
                //結果は、アプリが停止した。つまり、left[6]は存在しない。
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
                            seikai += String.valueOf(position_left + 1);
                            seikai += " ";
                           // seikai += String.valueOf(position_right + 1);
                           // seikai += " ";
                        }
                        score = array_[position_left][position_right];
                    } else if (score_left <= score_naname && score_left <= score_up && score > score_left) {
                        position_left = position_left;
                        position_right = position_right - 1;
                        if (score > score_left) {
                            //seikai += "-1";
                            //seikai += " ";
                            //seikai += String.valueOf(position_right + 1);
                            //seikai += " ";
                        }
                        score = array_[position_left][position_right];
                    } else if (score_up <= score_naname && score_up <= score_left && score > score_up) {
                        position_left = position_left - 1;
                        position_right = position_right;
                        if (score > score_up) {
                            seikai += String.valueOf(position_left + 1);
                            seikai += " ";
                            //seikai += "-1";
                            //seikai += " ";
                        }
                    } else {

                    }

                }
                //textView.setText(String.valueOf(array_[4][6]));
                return  seikai;
            }
        }

        String right = data2;//ゴジラ";
        String left = data;//"キングゴジラ";
        String right2;
        // String right = "";
        // String left = "";
        Levenstein_distance LD = new Levenstein_distance();
        String distance = LD.LevensteinDistance(left, right);
        textView2.setText(data2);
        textView.setText(data);
        int spanColor = Color.RED;
        if( distance.length() != 0) {
            SpannableStringBuilder ssb = new SpannableStringBuilder(data);
            String[] distance_index = distance.split(" ",0);
            int place = 10000;
            for (int i = 0; i < distance_index.length; i++) {
                place = Integer.parseInt(distance_index[i]);
                ssb.setSpan(new ForegroundColorSpan(spanColor), place-1,place,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(ssb);


            }
        }



    }
}
