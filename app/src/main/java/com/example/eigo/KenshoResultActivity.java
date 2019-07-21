package com.example.eigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                //結果は、アプリが停止した。つまり、left[6]は存在しない。
                int array_[][] = new int[left.length + 1][right.length + 1];
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
                return  array_left;
            }
        }

        String right = "applet";//ゴジラ";
        String left = "apple";//"キングゴジラ";
        String right2;
        // String right = "";
        // String left = "";
        Levenstein_distance LD = new Levenstein_distance();
        String distance = LD.LevensteinDistance(left, right);

    }
}
