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
        String message = "cycling is coming";
        //受け取った文章を単語に分割する
        message = message.replaceAll(",", "");
        String[] messagetango = message.split(" ", 0);
        //textView.setText(String.valueOf(messagetango.length));

        // EditTextからテキストを取得
        String mondaibun = "recycling is becoming";
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
                                // seikai += right[position_right];
                               seikai += String.valueOf(position_left + 1);
                                seikai += " ";
                                seikai += String.valueOf(position_right + 1);
                                //seikai += "naname";
                                seikai += " ";
                            }
                            //}
                            score = array_[position_left][position_right];
                       } else if (score_left <= score_naname && score_left <= score_up && score > score_left) {
                            position_left = position_left;
                            position_right = position_right - 1;
                            if (score > score_left) {
                                seikai += String.valueOf(position_left);
                                seikai += " ";
                                seikai += String.valueOf(position_right + 1);
                                //  seikai += right[position_right];
                                //seikai += "left";
                                seikai += " ";

                            }
                            score = array_[position_left][position_right];
                        } else if (score_up <= score_naname && score_up <= score_left && score > score_up) {
                            position_left = position_left - 1;
                            position_right = position_right;
                            if (score > score_up) {
                                seikai += String.valueOf(position_left + 1);
                                seikai += " ";
                                seikai += String.valueOf(position_right);
                                //      seikai += right[position_right - 1];
                                //seikai += "up";
                                seikai += " ";
                            }
                        } else {

                        }

                    }

                    //textView.setText(String.valueOf(array_[left.length-1][right.length-1]));

                    //double max_string_num = Math.max(array_left.length(), array_right.length());
                    //double normalized_LD = array_[array_left.length()][array_right.length()];
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


            String[] distance_position_index = distance.split(" ", -1);
            //textView.setText(distance_position_index[2]);
   //         String[] distance_position_index = distance_position[0].split(" ", -1);
    //        String[] blank_position_index = distance_position[1].split(" ", -1);
                textView.setText(message);
    //        int index = 0;
            String index = "";
            SpannableStringBuilder ssb = new SpannableStringBuilder(message);
            int trial = distance_position_index.length / 2;
            for( int i = 1; i < 1 + 2 * trial; i = i + 2){
                   index += distance_position_index[i];
                   index += " ";
            }
            String[] distance_right_index = index.split(" ",0);
            int place = 10000;
            String red_tango = "";
            String black_tango = "";
            int red = 10000;
            int black = 10000;
            for( int i = 0; i < trial; i++) {
                place = Integer.parseInt(distance_right_index[i]);
                red_tango += messagetango[place-1];
                 red = message.indexOf(red_tango);
                 if( place != messagetango.length) {
                     black_tango += messagetango[place];
                     black = message.indexOf(black_tango);
                     ssb.setSpan(new ForegroundColorSpan(spanColor), red, black - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                     textView.setText(ssb);
                 }
                 else{
                     ssb.setSpan(new ForegroundColorSpan(spanColor), red,message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                     textView.setText(ssb);
                 }
                red_tango = "";
                black_tango = "";
            }

   //         int index_blank = 0;
    //        int index_distance = 0;
    //        String left_position = "";
    //        int sign = 0;
    //        int same_index = 0;
    //        for (int i = 0; i < blank_position_index.length - 1; i++) {
     //           index_blank = Integer.parseInt(blank_position_index[i]);
      //          for (int j = 1; j < distance_position_index.length - 1; j = j+ 2) {
      //              index_distance = Integer.parseInt(distance_position_index[j]);
      //              if (index_blank > index_distance && index < index_distance) {
        //                ssb.setSpan(new ForegroundColorSpan(spanColor), index, index_blank - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
             //           textView.setText(ssb);
        //                break;
          //          }
         //           if( index_distance == 0 && index_blank > index_distance && index <= index_distance){
          //              ssb.setSpan(new ForegroundColorSpan(spanColor), index, index_blank - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
               //         textView.setText(ssb);
          //              break;
           //         }
            //        if (i == blank_position_index.length - 2 && index_blank < index_distance) {
             //           ssb.setSpan(new ForegroundColorSpan(spanColor), index_blank - 1, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                 //       textView.setText(ssb);
             //       }
            //        else if( index_blank == index_distance){
             //           same_index = index_blank;
             //           left_position += distance_position_index[j-1];
               //         left_position += " ";
             //       }

            //    }
            //    index = index_blank;

         //   }

        //    String[] left_array_index = left_position.split(" ",-1);
        //    for( int h = 0; h < left_array_index.length-1; h++) {
         //       String c1 = String.valueOf(mondaibun.charAt(Integer.parseInt(left_array_index[h]) - 1));
         //
         //       if( c1.equals(" ")){
         //           sign++;
         //       }
         //       else{

         //       }
         //   }
        //    int max = 0;
        //    for( int i = 0; i < blank_position_index.length-1; i++){
        //        if( max < Integer.parseInt(blank_position_index[i])){
        //            max = Integer.parseInt(blank_position_index[i]);
        //        }
        //    }

      //      String index1 = "";
      //      int count2= 0;
      //      if( sign == 0){
        //        for( int i = 0; i < blank_position_index.length-1; i++){
         //           if( same_index == Integer.parseInt(blank_position_index[i])  && max > same_index){
          //               index1 = blank_position_index[i+1];
            //             count2++;
                         //textView.setText(index1);
            //            break;
              //      }

          //      }
               // ssb.setSpan(new ForegroundColorSpan(spanColor), same_index, Integer.parseInt(index1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
               // textView.setText(ssb);
         //       if(count2 == 0){
          //         ssb.setSpan(new ForegroundColorSpan(spanColor), same_index, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   // textView.setText(ssb);
        //        }
       //         else{
         //            ssb.setSpan(new ForegroundColorSpan(spanColor), same_index, Integer.parseInt(index1)-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //textView.setText(ssb);
           //     }
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

        //if (answer != "") {
      //  String[] distance_tango = distance.split("", -1);
      //index_distance = new int[distance_position_index.length];
     // for( int i = 0; i < distance_position_index.length-1; i++){
     //     index_distance[i] = Integer.parseInt(distance_position_index[i]);
      //}
        //    textView.setText(String.valueOf(index_distance[0]));
       // for (int j = 1; j < distance_tango.length; j++) {
        //    if (message.contains(distance_tango[j])) {
        //        int index = message.indexOf(distance_tango[j]);
        //        if( index != 0) {
        //
          //      }
         //   }
        //}


            TextView textview2 = findViewById(R.id.resultLabel);
            //textview2.setText(ca2);
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

