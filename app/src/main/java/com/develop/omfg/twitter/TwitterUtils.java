package com.develop.omfg.twitter;

import android.content.SharedPreferences;

import oauth.signpost.OAuth;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;

/**
 * Created by omfg on 21.02.18.
 */

class TwitterUtils {
    public static boolean isAuthenticated(SharedPreferences preferences){
        String token  = preferences.getString(OAuth.OAUTH_TOKEN,"");
        String secret  = preferences.getString(OAuth.OAUTH_TOKEN_SECRET,"");

        AccessToken a = new AccessToken(token,secret);
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(Constats.CONSUMER_KEY,Constats.CONSUMER_SECRET);
        twitter.setOAuthAccessToken(a);

        try {
            twitter.getAccountSettings();
            return true;
        } catch (TwitterException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static void sendTweet(SharedPreferences preferences, String msg) throws Exception{
        String token  = preferences.getString(OAuth.OAUTH_TOKEN,"");
        String secret  = preferences.getString(OAuth.OAUTH_TOKEN_SECRET,"");

        AccessToken a = new AccessToken(token,secret);
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(Constats.CONSUMER_KEY,Constats.CONSUMER_SECRET);
        twitter.setOAuthAccessToken(a);
        twitter.updateStatus(msg);
    }
}
