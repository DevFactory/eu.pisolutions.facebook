package eu.pisolutions.facebook.api;

/**
 * Constants for the Facebook API.
 *
 * @author Laurent Pireyn
 */
public final class FacebookApi {
    public static final String SCHEME = "https";

    public static final String HOST = "facebook.com";
    public static final String GRAPH_HOST = "graph." + HOST;

    public static final String VERSION_1_0 = "1.0";
    public static final String VERSION_2_0 = "2.0";
    public static final String VERSION_2_1 = "2.1";
    public static final String VERSION_2_2 = "2.2";

    public static final String OAUTH_PATH = "/oauth";
    public static final String OAUTH_ACCESS_TOKEN_PATH = OAUTH_PATH + "/access_token";

    public static final String ME_PATH = "/me";
    public static final String SEARCH_PATH = "/search";

    private FacebookApi() {}
}
