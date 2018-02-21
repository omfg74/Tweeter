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

    final public static String	CALLBACK_SCHEME = "x-latify-oauth-twitter";
    final public static String	CALLBACK_URL = CALLBACK_SCHEME + "://callback";
}
