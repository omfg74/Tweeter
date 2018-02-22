package com.develop.omfg.twitter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.ecs.android.sample.twitter.R;

import java.sql.PreparedStatement;
import java.util.Date;

import oauth.signpost.OAuth;

public class AndroidTwitterSample extends Activity {
private SharedPreferences prefs;
private final Handler mTwitterHandler  = new Handler();
private TextView loginStatus;
Button tweet;
Button clearCreditals = findViewById(R.id.clear_button);
final Runnable mUpdateTwitterNotification  = new Runnable() {
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
                if (TwitterUtils.isAuthenticated(prefs)) {
                    sendTweet();
                } else {
                    Intent i = new Intent(getApplication(), PrepareRequestTokenActivity.class);
                    i.putExtra("Test_msg", getTweetMsg());
                    startActivity(i);

                }
            }
        });
        clearCreditals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCredentials();
                updateLoginStatus();

            }
        });
    }
    public void updateLoginStatus(){
        loginStatus.setText("Logged into Twitter : " + TwitterUtils.isAuthenticated(prefs));

    }
    private String getTweetMsg() {
        return "Tweeting from Android App at " + new Date().toLocaleString();
    }
    public void sendTweet() {
        Thread t = new Thread() {
            public void run() {

                try {
                    TwitterUtils.sendTweet(prefs,getTweetMsg());
                    mTwitterHandler.post(mUpdateTwitterNotification);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        };
        t.start();
    }
    private void clearCredentials() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final Editor edit = prefs.edit();
        edit.remove(OAuth.OAUTH_TOKEN);
        edit.remove(OAuth.OAUTH_TOKEN_SECRET);
        edit.commit();
    }
}




