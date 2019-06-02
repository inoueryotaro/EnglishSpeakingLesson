package com.example.eigo;

import android.os.Build;
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

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import java.util.HashMap;

import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

public  class MainActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {
    // リクエストを認識するための変数宣言。適当な数字でいい
    private static final int REQUEST_CODE = 1000;
    private TextView textView;
    private TextToSpeech tts;
    private static final String TAG = "TestTTS";

    public static final String EXTRA_MESSAGE
            = "com.example.eigo.MESSAGE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, this);

        textView = (TextView) findViewById(R.id.text_view4);
        Button buttonStart = (Button) findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                speech();
            }
        });
        Button ttsButton = findViewById(R.id.button_start2);
        ttsButton.setOnClickListener(this);

    }

    public void onInit(int status) {
        // TTS初期化
        if (TextToSpeech.SUCCESS == status) {
            Log.d(TAG, "initialized");
        } else {
            Log.e(TAG, "failed to initialize");
        }
    }

    @Override
    public void onClick(View v) {
        speechText();
    }

    private void shutDown() {
        if (null != tts) {
            // to release the resource of TextToSpeech
            tts.shutdown();
        }
    }

    private void speechText() {
        TextView editor = findViewById(R.id.text_view2);

        // EditTextからテキストを取得
        String string = editor.getText().toString();

        if (0 < string.length()) {
            if (tts.isSpeaking()) {
                tts.stop();
                return;
            }
            setSpeechRate(1.0f);
            setSpeechPitch(1.0f);

            if (Build.VERSION.SDK_INT >= 21) {
                // SDK 21 以上
                tts.speak(string, TextToSpeech.QUEUE_FLUSH, null, "messageID");
            } else {
                // tts.speak(text, TextToSpeech.QUEUE_FLUSH, null) に
                // KEY_PARAM_UTTERANCE_ID を HasMap で設定
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "messageID");
                tts.speak(string, TextToSpeech.QUEUE_FLUSH, map);
            }

            setTtsListener();
        }
    }

    private void setSpeechRate(float rate) {
        if (null != tts) {
            tts.setSpeechRate(rate);
        }
    }

    // 読み上げのピッチ
    private void setSpeechPitch(float pitch) {
        if (null != tts) {
            tts.setPitch(pitch);
        }
    }

    // 読み上げの始まりと終わりを取得
    private void setTtsListener() {
        // android version more than 15th
        if (Build.VERSION.SDK_INT >= 15) {
            int listenerResult =
                    tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onDone(String utteranceId) {
                            Log.d(TAG, "progress on Done " + utteranceId);
                        }

                        @Override
                        public void onError(String utteranceId) {
                            Log.d(TAG, "progress on Error " + utteranceId);
                        }

                        @Override
                        public void onStart(String utteranceId) {
                            Log.d(TAG, "progress on Start " + utteranceId);
                        }
                    });

            if (listenerResult != TextToSpeech.SUCCESS) {
                Log.e(TAG, "failed to add utterance progress listener");
            }
        } else {
            Log.e(TAG, "Build VERSION is less than API 15");
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        shutDown();
    }

    private void speech() {
        //音声認識プロンプトを立ち上げるインテント作成
        try{
         Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
         intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH.toString());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,100);
        //インテント発行
         startActivityForResult(intent, REQUEST_CODE);

        }catch (ActivityNotFoundException e){
        //エラー表示
          e.printStackTrace();
         textView.setText(R.string.error);
        }
    }

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
            Intent intent1 = new Intent(getApplication(), ResultActivity.class);
            if (textView.getText() != null) {
                String str = textView.getText().toString();
                intent1.putExtra(EXTRA_MESSAGE, str);
            }
            startActivity(intent1);
            textView.setText("");
        }


    }


