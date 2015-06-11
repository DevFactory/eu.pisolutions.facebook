package eu.pisolutions.facebook.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import static java.util.Objects.requireNonNull;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import eu.pisolutions.facebook.api.security.FacebookApiApplicationCredentials;

/**
 * {@link FacebookApiClient} that uses a {@link FacebookApiApplicationCredentials}.
 *
 * @author Laurent Pireyn
 */
public final class SimpleFacebookApiClient implements FacebookApiClient {
    private static final String ACCESS_TOKEN = "access_token";

    private static String createAccessToken(FacebookApiApplicationCredentials credentials) {
        return Long.toString(credentials.getId()) + '|' + credentials.getSecret();
    }

    private static void handleError(CloseableHttpResponse response) throws IOException {
        final StatusLine statusLine = response.getStatusLine();
        if (statusLine == null) {
            return;
        }
        final int statusCode = statusLine.getStatusCode();
        if (statusCode != 200) {
            FacebookApiError error = null;
            try {
                error = Http.readValue(response.getEntity(), Json.FACEBOOK_API_ERROR_TYPE);
            } catch (IOException e) {
                // Ignore
            }
            throw new FacebookApiException(error);
        }
    }

    private final CloseableHttpClient httpClient;
    private final String accessToken;

    public SimpleFacebookApiClient(CloseableHttpClient httpClient, FacebookApiApplicationCredentials credentials) {
        this.httpClient = requireNonNull(httpClient);
        accessToken = createAccessToken(requireNonNull(credentials));
    }

    private URI createUrl(String path, Map<String, String> parameters) {
        try {
            return new URIBuilder()
                .setScheme(FacebookApi.SCHEME)
                .setHost(FacebookApi.GRAPH_HOST)
                .setPath("/v" + FacebookApi.VERSION_2_2 + path)
                .addParameter(ACCESS_TOKEN, accessToken)
                .addParameters(Http.toNameValuePairs(parameters))
            .build();
        } catch (URISyntaxException e) {
            throw new AssertionError(e);
        }
    }

    private HttpGet createGetRequest(String path, Map<String, String> parameters) {
        return new HttpGet(createUrl(path, parameters));
    }

    private CloseableHttpResponse execute(HttpUriRequest request) throws IOException {
        final CloseableHttpResponse response = httpClient.execute(request);
        handleError(response);
        return response;
    }

    private CloseableHttpResponse executeGet(String path, Map<String, String> parameters) throws IOException {
        return execute(createGetRequest(path, parameters));
    }

    private <T extends Response> T executeGet(String path, Map<String, String> parameters, T response) throws IOException {
        try (final CloseableHttpResponse httpResponse = executeGet(path, parameters)) {
            response.setDate(Http.getDate(httpResponse));
            response.setData(Http.<Map<String, Object>>readValue(httpResponse.getEntity(), Json.DATA_TYPE));
        }
        return response;
    }

    @Override
    public GetUrlResponse getUrl(GetUrlParameters parameters) throws IOException {
        return executeGet(
            '/' + parameters.getUrl(),
            Collections.<String, String>emptyMap(),
            new GetUrlResponse()
        );
    }
}
