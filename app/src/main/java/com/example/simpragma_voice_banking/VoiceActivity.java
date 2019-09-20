package com.example.simpragma_voice_banking;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.simpragma_voice_banking.adapter.ChatListAdapter;
import com.example.simpragma_voice_banking.model.ChatMessage;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VoiceActivity extends Activity implements RecognitionListener, TextToSpeech.OnInitListener {

    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQUEST_RECORD_PERMISSION = 100;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent intent;
    private String LOG_TAG = "loggingMessage";
    ProgressBar spin_progressBar;
    RecyclerView recyclerView;
    public ChatListAdapter chatListAdapter;
    public List<ChatMessage> titleList = new ArrayList<>();
    TextToSpeech mTTS = null;
    private final int ACT_CHECK_TTS_DATA = 1000;
    ChatMessage chatMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
         speech = SpeechRecognizer.createSpeechRecognizer(this);
        Log.i(LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(this));
        speech.setRecognitionListener(this);
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());


        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }

    private void setUpView() {
        txtSpeechInput = (TextView) findViewById(R.id.voice_text);
        btnSpeak = (ImageButton) findViewById(R.id.mic);
        spin_progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite wave = new Wave();
        spin_progressBar.setIndeterminateDrawable(wave);
        spin_progressBar.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recy_desh);

        chatListAdapter = new ChatListAdapter(titleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(chatListAdapter);

        // checking text to speech language
        Intent ttsIntent = new Intent();
        ttsIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(ttsIntent, ACT_CHECK_TTS_DATA);
       // prepareMessage();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACT_CHECK_TTS_DATA) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS
            ) {
                // Data exists, so we instantiate the TTS engine
                mTTS = new TextToSpeech(this, this, "com.google.android.tts");
                mTTS.setPitch((float) 0.5);

            } else {
                // Data is missing, so we start the TTS
                // installation process
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    private void prepareMessage(String message) {
         chatMessage = new ChatMessage(message,"","sender","");
        titleList.add(chatMessage);
        chatListAdapter.notifyDataSetChanged();


        setDummyReceiverMessage(message);

    }

    private void setDummyReceiverMessage(String message) {
        String msg="";
        if (message.contains("help")){
            msg = "How can i help you";
            setMessageToList(msg);

        }else if (message.contains("number")){
            msg = "your account number is  40850201063";
            setMessageToList(msg);

        }else if (message.contains("credit")){
            msg = "yes, a payment of 50000 rupees was made from simpragma";
            setMessageToList(msg);

        }else if(message.contains("balance")){
            msg = "your balance is 150000 rupees";
            setMessageToList(msg);

        }else {
            msg = "sorry   anything else";
            setMessageToList(msg);

        }
    }

    private void setMessageToList(String message) {
        chatMessage = new ChatMessage(message,"","receiver","");
        titleList.add(chatMessage);
        chatListAdapter.notifyDataSetChanged();
        SpeakOut(message);

    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        // spin_progressBar.setVisibility(View.VISIBLE);
        Log.e(LOG_TAG, "ButtonClick");
        ActivityCompat.requestPermissions
                (VoiceActivity.this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_RECORD_PERMISSION);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    speech.startListening(intent);
                } else {
                    Toast.makeText(VoiceActivity.this, "Permission Denied!", Toast
                            .LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (speech != null) {
            speech.destroy();
            Log.e(LOG_TAG, "destroy");
        }
    }

    /**
     * Receiving speech input
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        ///getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        spin_progressBar.setVisibility(View.VISIBLE);
        Log.e(LOG_TAG, "Voice recording starts");
        txtSpeechInput.setText("Listening ...");

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.e(LOG_TAG, "Voice Beginning");


    }

    @Override
    public void onRmsChanged(float rmsdB) {
        //   Log.e(LOG_TAG, "onRmsChanged: " + rmsdB);
        //progressBar.setProgress((int) rmsdB);
    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        Log.e(LOG_TAG, "Speech End");
        speech.stopListening();
        spin_progressBar.setVisibility(View.INVISIBLE);
        txtSpeechInput.setText("Tap to speak");

    }

    @Override
    public void onError(int i) {
        Log.e(LOG_TAG, "On Error");

    }

    @Override
    public void onResults(Bundle results) {
        Log.e(LOG_TAG, "onResults");


        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches)
            text += result + "\n";
        System.out.println("result " + text);
        Log.e(LOG_TAG, "onResult " + matches.get(0));
        if (matches.get(0).length()>0){
            prepareMessage(matches.get(0));
        }



    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            if (mTTS != null) {
                int result = mTTS.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "TTS language is not" +
                            " supported", Toast.LENGTH_LONG).show();
                } else {
                    setMessageToList("Welcome to Voice banking System");
                    setMessageToList("How can i help you today ");

                    // SpeakOut("TTS is ready", 0);
                }
            }
        } else {
            Toast.makeText(this, "TTS initialization failed",
                    Toast.LENGTH_LONG).show();
        }
        
    }

    private void SpeakOut(String message) {

        mTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}
