package com.example.eigo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageView iv = (ImageView)this.findViewById(R.id.tangen_1_checkView);
        ImageView iv2 = (ImageView)this.findViewById(R.id.tangen_2_checkView);
        ImageView iv3 = (ImageView)this.findViewById(R.id.tangen_3_checkView);
        ImageView iv4 = (ImageView)this.findViewById(R.id.tangen_4_checkView);
        ImageView iv5 = (ImageView)this.findViewById(R.id.tangen_5_checkView);
        ImageView iv6 = (ImageView)this.findViewById(R.id.tangen_6_checkView);
        ImageView iv7 = (ImageView)this.findViewById(R.id.tangen_7_checkView);
        ImageView iv8 = (ImageView)this.findViewById(R.id.tangen_8_checkView);
        ImageView iv9 = (ImageView)this.findViewById(R.id.tangen_9_checkView);
        ImageView iv10 = (ImageView)this.findViewById(R.id.tangen_10_checkView);
        ImageView iv11 = (ImageView)this.findViewById(R.id.tangen_11_checkView);
        ImageView iv12 = (ImageView)this.findViewById(R.id.tangen_12_checkView);


        Button tangen_1button = (Button) findViewById(R.id.tangen_1);
        tangen_1button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                start();
            }

            private void start() {
                Intent intent0 = new Intent(getApplication(), MainActivity.class);
                startActivity(intent0);
            }
        });
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs4 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs5 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs6 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs7 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs8 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs9 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs10 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs11 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs12 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Intent intent1 = getIntent();
        int data = intent1.getIntExtra("keyword",0);
        if(data == 1){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("int", 1);
            editor.apply();
        }
        Intent intent2 = getIntent();
        int data2 = intent2.getIntExtra("keyword2",0);
        if(data2 == 1){
            SharedPreferences.Editor editor = prefs2.edit();
            editor.putInt("int2", 1);
            editor.apply();
        }
        Intent intent3 = getIntent();
        int data3 = intent3.getIntExtra("keyword3",0);
        if(data3 == 1){
            SharedPreferences.Editor editor = prefs3.edit();
            editor.putInt("int3", 1);
            editor.apply();
        }
        Intent intent4 = getIntent();
        int data4 = intent4.getIntExtra("keyword4",0);
        if(data4 == 1){
            SharedPreferences.Editor editor = prefs4.edit();
            editor.putInt("int4", 1);
            editor.apply();
        }
        Intent intent5 = getIntent();
        int data5 = intent5.getIntExtra("keyword5",0);
        if(data5 == 1){
            SharedPreferences.Editor editor = prefs5.edit();
            editor.putInt("int5", 1);
            editor.apply();
        }
        Intent intent6 = getIntent();
        int data6 = intent6.getIntExtra("keyword6",0);
        if(data6 == 1){
            SharedPreferences.Editor editor = prefs6.edit();
            editor.putInt("int6", 1);
            editor.apply();
        }
        Intent intent7 = getIntent();
        int data7 = intent7.getIntExtra("keyword7",0);
        if(data7 == 1){
            SharedPreferences.Editor editor = prefs7.edit();
            editor.putInt("int7", 1);
            editor.apply();
        }
        Intent intent8 = getIntent();
        int data8 = intent8.getIntExtra("keyword8",0);
        if(data8 == 1){
            SharedPreferences.Editor editor = prefs8.edit();
            editor.putInt("int8", 1);
            editor.apply();
        }
        Intent intent9 = getIntent();
        int data9 = intent9.getIntExtra("keyword9",0);
        if(data9 == 1){
            SharedPreferences.Editor editor = prefs9.edit();
            editor.putInt("int9", 1);
            editor.apply();
        }
        Intent intent10 = getIntent();
        int data10 = intent10.getIntExtra("keyword10",0);
        if(data10 == 1){
            SharedPreferences.Editor editor = prefs10.edit();
            editor.putInt("int10", 1);
            editor.apply();
        }
        Intent intent11 = getIntent();
        int data11 = intent11.getIntExtra("keyword11",0);
        if(data11 == 1){
            SharedPreferences.Editor editor = prefs11.edit();
            editor.putInt("int11", 1);
            editor.apply();
        }
        Intent intent12 = getIntent();
        int data12 = intent12.getIntExtra("keyword12",0);
        if(data12 == 1){
            SharedPreferences.Editor editor = prefs12.edit();
            editor.putInt("int12", 1);
            editor.apply();
        }


        int intNum = prefs.getInt("int",0);
        if( intNum == 1){
            iv.setImageResource(R.drawable.check_picture);
        }
        else{
            iv.setImageDrawable(null);
        }
        int intNum2 = prefs2.getInt("int2",0);
        if( intNum2 == 1){
            iv2.setImageResource(R.drawable.check_picture);
        }
        else{
            iv2.setImageDrawable(null);
        }
        int intNum3 = prefs3.getInt("int3",0);
        if( intNum3 == 1){
            iv3.setImageResource(R.drawable.check_picture);
        }
        else{
            iv3.setImageDrawable(null);
        }
        int intNum4 = prefs4.getInt("int4",0);
        if( intNum4 == 1){
            iv4.setImageResource(R.drawable.check_picture);
        }
        else{
            iv4.setImageDrawable(null);
        }
        int intNum5 = prefs5.getInt("int5",0);
        if( intNum5 == 1){
            iv5.setImageResource(R.drawable.check_picture);
        }
        else{
            iv5.setImageDrawable(null);
        }
        int intNum6 = prefs6.getInt("int6",0);
        if( intNum6 == 1){
            iv6.setImageResource(R.drawable.check_picture);
        }
        else{
            iv6.setImageDrawable(null);
        }
        int intNum7 = prefs7.getInt("int7",0);
        if( intNum7 == 1){
            iv7.setImageResource(R.drawable.check_picture);
        }
        else{
            iv7.setImageDrawable(null);
        }
        int intNum8 = prefs8.getInt("int8",0);
        if( intNum8 == 1){
            iv8.setImageResource(R.drawable.check_picture);
        }
        else{
            iv8.setImageDrawable(null);
        }
        int intNum9 = prefs9.getInt("int9",0);
        if( intNum9 == 1){
            iv9.setImageResource(R.drawable.check_picture);
        }
        else{
            iv9.setImageDrawable(null);
        }
        int intNum10 = prefs10.getInt("int10",0);
        if( intNum10 == 1){
            iv10.setImageResource(R.drawable.check_picture);
        }
        else{
            iv10.setImageDrawable(null);
        }
        int intNum11 = prefs11.getInt("int11",0);
        if( intNum11 == 1){
            iv11.setImageResource(R.drawable.check_picture);
        }
        else{
            iv11.setImageDrawable(null);
        }
        int intNum12 = prefs12.getInt("int12",0);
        if( intNum12 == 1){
            iv12.setImageResource(R.drawable.check_picture);
        }
        else{
            iv12.setImageDrawable(null);
        }


    }
}
