package com.develop.omfg.twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import

import java.sql.PreparedStatement;

public class MainActivity extends AppCompatActivity {
Button tweet;
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
