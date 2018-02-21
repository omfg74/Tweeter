package com.develop.omfg.twitter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.PreparedStatement;

public class MainActivity extends Activity {
private SharedPreferences prefs;
private final Handler mTwitterHandler  = new Handler();
private TextView loginStatus;
Button tweet;

final Runnable mUpdateTwiterNotification  = new Runnable() {
    @Override
    public void run() {
        Toast.makeText(getBaseContext(),"Tweet send !",Toast.LENGTH_LONG).show();

    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tweet = findViewById(R.id.tweet_button);
    tweet.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(TwitterUtils.isAuthenticated(prefs)){
                sendTweet();
            }else {
                Intent i =  new Intent(getApplication(), PrepareRequestTokenActivity.class);
                i.putExtra("Test_msg",getTweetMsg());
                startActivity(i);

            }
        }
    });
    }


}
