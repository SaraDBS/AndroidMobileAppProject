package com.example.home.latestnewsreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    public void onClickChangeToActivity(View view){
        Intent i;
        i = new Intent(getApplicationContext(),MainActivity.class);

        startActivity(i);
    }

    public void onClickChangeToBookActivity(View view){
        Intent b;
        b = new Intent(getApplicationContext(),BookActivity.class);

        startActivity(b);
    }

    public void onClickFindBooks(View view){
        Intent c;
        c = new Intent(getApplicationContext(),MapsActivity.class);
       // c.putExtra("locationinfo");
        startActivity(c);
    }

    public void onClickCamera(View view){
        Intent d;
        d = new Intent(getApplicationContext(),CameraActivity.class);
        startActivity(d);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
}
