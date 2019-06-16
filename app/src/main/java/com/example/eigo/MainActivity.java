package com.example.eigo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;
import java.io.IOException;
import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;


import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import java.util.HashMap;
import android.widget.Toast;

import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
public  class MainActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {
    // リクエストを認識するための変数宣言。適当な数字でいい


    private SpeechRecognizer sr;
    private static final int REQUEST_CODE = 1000;
    private TextView textView;
    private TextToSpeech tts;
    private static final String TAG = "TestTTS";

    public static final String EXTRA_MESSAGE
            = "com.example.eigo.MESSAGE";

 //   MediaRecorder recorder = null;
  //  final static String FILENAME = "/Users/inoue/Desktop/test.3gp";
   // boolean bIsRecording = false;
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
     //   recorder = new MediaRecorder();
      //  recorder.setOutputFile(FILENAME);

        //    if (!bIsRecording) {
// 入力ソースの指定
        //recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        //try {
            // 録音準備
        //recorder.prepare();
          //  } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //} catch (IOException e) {
              //  // TODO Auto-generated catch block
                //    e.printStackTrace();
                  //  }
                    // 録音開始
                    //recorder.start();
                  //  btn.setText(R.string.stop_label);
                    //bIsRecording = true;
                   // }
                //else {
                    // 録音停止
                  //  recorder.stop();
                    //recorder.reset();
                    //
                // btn.setText(R.string.start_label);
                    //bIsRecording = false;
                //}





        try{
            if (sr == null){
                sr = SpeechRecognizer.createSpeechRecognizer(this);
                if (!SpeechRecognizer.isRecognitionAvailable(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "音声認識が使えません",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
                sr.setRecognitionListener(new listener());
            }
         Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
         intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH.toString());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,100);
            sr.startListening(intent);
        //インテント発行
        // startActivityForResult(intent, REQUEST_CODE);

        }catch (Exception ex){
        //エラー表示
            Toast.makeText(getApplicationContext(), "startListening()でエラーが起こりました",
                    Toast.LENGTH_LONG).show();
            finish();
          //e.printStackTrace();
         //textView.setText(R.string.error);
        }


    }


    protected void stopListening() {
        if (sr != null) sr.destroy();
        sr = null;
    }
    //public void restartListeningService() {
      //  stopListening();
        //speech();
    //}
    //protected void onResume() {
      //  super.onResume();
        //speech();
    //}
    //protected void onPause() {
      //  stopListening();
       // super.onPause();
   // }


//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  //            super.onActivityResult(requestCode, resultCode, data);

    //        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // 認識結果を ArrayList で取得
      //        ArrayList<String> candidates =
        //            data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

          //  if(candidates.size() > 0) {
            // 認識結果候補で一番有力なものを表示
            //  textView.setText( candidates.get(0));
            //}
             //}
            //Intent intent1 = new Intent(getApplication(), ResultActivity.class);
            //if (textView.getText() != null) {
              //  String str = textView.getText().toString();
                //intent1.putExtra(EXTRA_MESSAGE, str);
           // }
            //startActivity(intent1);
        //    textView.setText("");
        //}


    private class listener implements RecognitionListener {
        public void onBeginningOfSpeech() {
            /*Toast.makeText(getApplicationContext(), "onBeginningofSpeech",
                    Toast.LENGTH_SHORT).show();*/
        }
        public void onBufferReceived(byte[] buffer) {
        }
        public void onEndOfSpeech() {
            /*Toast.makeText(getApplicationContext(), "onEndofSpeech",
                    Toast.LENGTH_SHORT).show();*/
        }
        public void onError(int error) {
            String reason = "";
            switch (error) {
                // Audio recording error
                case SpeechRecognizer.ERROR_AUDIO:
                    reason = "ERROR_AUDIO";
                    break;
                // Other client side errors
                case SpeechRecognizer.ERROR_CLIENT:
                    reason = "ERROR_CLIENT";
                    break;
                // Insufficient permissions
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    reason = "ERROR_INSUFFICIENT_PERMISSIONS";
                    break;
                // 	Other network related errors
                case SpeechRecognizer.ERROR_NETWORK:
                    reason = "ERROR_NETWORK";
                    /* ネットワーク接続をチェックする処理をここに入れる */
                    break;
                // Network operation timed out
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    reason = "ERROR_NETWORK_TIMEOUT";
                    break;
                // No recognition result matched
                case SpeechRecognizer.ERROR_NO_MATCH:
                    reason = "ERROR_NO_MATCH";
                    break;
                // RecognitionService busy
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    reason = "ERROR_RECOGNIZER_BUSY";
                    break;
                // Server sends error status
                case SpeechRecognizer.ERROR_SERVER:
                    reason = "ERROR_SERVER";
                    /* ネットワーク接続をチェックをする処理をここに入れる */
                    break;
                // No speech input
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    reason = "ERROR_SPEECH_TIMEOUT";
                    break;
            }
            Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_SHORT).show();
      //      restartListeningService();
        }
        public void onEvent(int eventType, Bundle params) {
        }

        // 部分的な認識結果が利用出来るときに呼ばれる
        // 利用するにはインテントでEXTRA_PARTIAL_RESULTSを指定する必要がある
        public void onPartialResults(Bundle partialResults) {
        }
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(getApplicationContext(), "話してください",
                    Toast.LENGTH_SHORT).show();
        }
        public void onResults(Bundle results) {
            // 結果をArrayListとして取得
            //ArrayList<String> candidates =
                    //            data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    //  if(candidates.size() > 0) {
                    // 認識結果候補で一番有力なものを表示
                    //  textView.setText( candidates.get(0));
            ArrayList<String> results_array = results.getStringArrayList(
                    SpeechRecognizer.RESULTS_RECOGNITION);
                        // 取得した文字列を結合
            String resultsString = "";
            for (int i = 0; i < results.size(); i++) {
                resultsString += results_array.get(i) + ";";
            }
            // トーストを使って結果表示
            if (results_array.size() > 0){
            textView.setText(results_array.get(0));}
            //Toast.makeText(getApplicationContext(), resultsString, Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(getApplication(), ResultActivity.class);

            if (textView.getText() != null) {
              String str = textView.getText().toString();
            intent1.putExtra(EXTRA_MESSAGE, str);
             }
            startActivity(intent1);
            textView.setText("");
        //    restartListeningService();
        }
        public void onRmsChanged(float rmsdB) {
        }

    }



}


