package com.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import de.greenrobot.event.EventBus;

public class SecondActivity extends AppCompatActivity {

    private static String ENCRYPTION_KEY_STRING = "kvCmh5hShIF7BVFZQSOUWw==:Nm/Ad93XPNMwkL5RDmyeqfzd8DNAqsKCj5n0iZpsOLM=";
    TextView tv1, tv2, tv3, tv4, tv5;
    StringEncrypter.SecretKeys mEncryptionKey;

    private String sampleString = "TeamAndroid : Chetan, Dhananjay, Pallavi, Yashswita";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);

        tv3.setText("Original String : " + sampleString);

        /**
         * Generate key for one time and save for use every time.
         * The generated key changes every time upon execution of this statement.
         * Same key has to be used for encryption and decryption.
         */

/*
        try {
            StringEncrypter.SecretKeys key = StringEncrypter.generateKey();

            Log.i("Encryption Key : ", ""+key);

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
*/


        try {

            mEncryptionKey = StringEncrypter.keys(ENCRYPTION_KEY_STRING);
            StringEncrypter.CipherTextIvMac textIvMac = StringEncrypter.encrypt(sampleString, mEncryptionKey);

            String encryptedString = textIvMac.toString();
            tv4.setText("Encrypted String : " + encryptedString);

            String decryptedString = StringEncrypter.decryptString(textIvMac, mEncryptionKey);
            tv5.setText("Decrypted String : " + decryptedString);


        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEventMainThread(User user){

        tv1.setText("Received Name : " + user.getName());
        tv2.setText("Received Company : " + user.getCompany());
    }
}
