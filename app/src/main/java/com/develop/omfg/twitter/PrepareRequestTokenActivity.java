package com.develop.omfg.twitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

/**
 * Created by omfg7 on 21.02.2018.
 */

class PrepareRequestTokenActivity extends Activity{
    final String TAG = getClass().getName();

    private OAuthConsumer consumer;
    private OAuthProvider provider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            this.consumer = new CommonsHttpOAuthConsumer(Constats.CONSUMER_KEY,Constats.CONSUMER_SECRET);
            this.provider = new CommonsHttpOAuthProvider(Constats.REQUEST_URL,Constats.ACCESS_URL,Constats.AUTHORIZE_URL);

        }catch (Exception e){
            Log.e(TAG,"\"Error creating consumer / provider\"",e);
        }
        Log.i(TAG, "Starting task to retrieve request token.");
        new OAuthRequestTokenTask(this,consumer,provider).execute();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final Uri uri = intent.getData();
        if(uri != null && uri.getScheme().equals(Constats.OAUTH_CALLBACK_SCHEME)){
            Log.i(TAG, "Callback received : " + uri);
            Log.i(TAG, "Retrieving Access Token");
            new RetrieveAccessTokenTask(this,consumer,provider,prefs).execute(uri);
            finish();
        }
    }
    public class  RetrieveAccessTokenTask extends AsyncTask<Uri,Void,Void>{
        private Context context;
        private OAuthProvider provider;
        private OAuthConsumer consumer;
        private SharedPreferences prefs;
        public RetrieveAccessTokenTask(Context context, OAuthConsumer consumer,OAuthProvider provider, SharedPreferences prefs) {
            this.context = context;
            this.consumer = consumer;
            this.provider = provider;
            this.prefs=prefs;
        }

        @Override
        protected Void doInBackground(Uri...params) {
            final Uri uri = params[0];
            final String oauth_verifier  = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
            try{
                provider.retrieveAccessToken(consumer,oauth_verifier);
            }catch (Exception e){

            }
            return null;
        }
    }
}