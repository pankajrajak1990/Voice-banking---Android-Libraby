package com.example.simpragma_voice_banking.ui.activity.login

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import com.example.simpragma_voice_banking.R
import com.example.simpragma_voice_banking.VoiceActivity
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }
     fun IntentToVoice(view : View){
         var intent: Intent = Intent(applicationContext, VoiceActivity::class.java)
         startActivity(intent)
         finish()

     }
}
