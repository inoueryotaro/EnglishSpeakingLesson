package com.example.eigo;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class KenshoActivity extends AppCompatActivity {

    private SpeechRecognizer sr;
    private  TextView textView;
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kensho);

        textView = findViewById(R.id.mondai_tango_text);
        textView2 = findViewById(R.id.onsei);
        Button button = (Button) findViewById(R.id.speaking_button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                speech();
            }
        });

        Intent intent = getIntent();
        String data1 = intent.getStringExtra("miss");
        String[] miss_tango_box = data1.split(" ",0);
        textView.setText(miss_tango_box[0]);


    }
    private void speech() {
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
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH.toString());
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
            sr.startListening(intent);

        }catch (Exception ex){
            //エラー表示
            Toast.makeText(getApplicationContext(), "startListening()でエラーが起こりました",
                    Toast.LENGTH_LONG).show();
            finish();
        }


    }


    protected void stopListening() {
        if (sr != null) sr.destroy();
        sr = null;
    }

    private class listener implements RecognitionListener {
        public void onBeginningOfSpeech() {
        }
        public void onBufferReceived(byte[] buffer) {
        }
        public void onEndOfSpeech() {
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
                textView2.setText(results_array.get(0));}
            //Toast.makeText(getApplicationContext(), resultsString, Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(getApplication(), KenshoResultActivity.class);

            if (textView2.getText() != null) {
                String str = textView.getText().toString();
                String str2 = textView2.getText().toString();
                intent2.putExtra("kenshomondai",str);
                intent2.putExtra("kenshoresult", str2);
            }
            startActivity(intent2);
            textView2.setText("");
            //    restartListeningService();
        }
        public void onRmsChanged(float rmsdB) {
        }

    }
}
