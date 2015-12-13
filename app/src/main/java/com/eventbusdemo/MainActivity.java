package com.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNext = (Button) findViewById(R.id.btnnext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User("Dhananjay Kulkarni", "Quadlogix");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                EventBus.getDefault().postSticky(user);
                startActivity(intent);
            }
        });
    }
}
