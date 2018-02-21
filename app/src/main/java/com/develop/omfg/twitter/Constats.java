package com.develop.omfg.twitter;

/**
 * Created by omfg on 21.02.18.
 */

public class Constats {
    public static final String CONSUMER_KEY = "p6Kw1WbrT13yn8pnhEsgXUiON";
    public static final String CONSUMER_SECRET= "pnu55iPFjIDjcVwOK6AHQ989VCDm1UDLx7crK5fV9NJyTfZRso";

    public static final String REQUEST_URL = "http://api.twitter.com/oauth/request_token";
    public static final String ACCESS_URL = "http://api.twitter.com/oauth/access_token";
    public static final String AUTHORIZE_URL = "http://api.twitter.com/oauth/authorize";

    public static final String	OAUTH_CALLBACK_SCHEME	= "x-oauthflow-twitter";
    public static final String	OAUTH_CALLBACK_HOST		= "callback";
    public static final String	OAUTH_CALLBACK_URL		= OAUTH_CALLBACK_SCHEME + "://" + OAUTH_CALLBACK_HOST;
}
