package com.sundar.fingerprint_authentication

import android.content.Intent
import android.hardware.biometrics.BiometricPrompt
import android.hardware.biometrics.BiometricPrompt.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor
import androidx.biometric.BiometricPrompt as AndroidxBiometricBiometricPrompt

class MainActivity : AppCompatActivity() {

    private val TAG = "BuyAddressScreenTAG"
    private val activity = this@MainActivity

     lateinit var executor: Executor
     lateinit var biometricPrompt: BiometricPrompt
     lateinit var promptInfo: AndroidxBiometricBiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        executor = ContextCompat.getMainExecutor(activity)
      /*  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            biometricPrompt = BiometricPrompt(activity, executor,
                object : BiometricPrompt.AuthenticationCallback() {

                })
        }*/

        androidx.biometric.BiometricPrompt(activity, executor, object : androidx.biometric.BiometricPrompt.AuthenticationCallback(){

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                //if any error
                tvAutoStatus.text = "Error" + errString
            }

            override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                tvAutoStatus.text = "Successfull Authenticaton"
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                tvAutoStatus.text = "Failed Authenticaton"
            }
        })



        //Todo setup tittle, subtittle description on dialog
        promptInfo=androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Login using fingerprint of face")
            .setNegativeButtonText("Cancel")
            .build()


        button_auth.setOnClickListener {
            //biometricPrompt.authenticate(promptInfo)
          //  biometricPrompt.aut

          //  biometricPrompt.authenticate(promptInfo.toString())

            /*biometricPrompt.authenticate(androidx.biometric.BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login using fingerprint of face")
                .setNegativeButtonText("Cancel")
                .build()
            )*/
        }

    }

}