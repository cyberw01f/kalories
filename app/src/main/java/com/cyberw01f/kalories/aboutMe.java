package com.cyberw01f.kalories;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class aboutMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

    }

    public void openChrome (View v){
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/cyberw01f/kalories"));
        startActivity(i);
    }
}
