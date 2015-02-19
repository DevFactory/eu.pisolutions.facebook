package eu.pisolutions.facebook.api;

import java.io.IOException;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import eu.pisolutions.facebook.api.FacebookApiClient.GetUrlParameters;
import eu.pisolutions.facebook.api.FacebookApiClient.GetUrlResponse;
import eu.pisolutions.facebook.api.security.FacebookApiApplicationCredentials;

/**
 * @author Laurent Pireyn
 */
public class SimpleFacebookApiClientTest {
    private static final FacebookApiApplicationCredentials CREDENTIALS = new FacebookApiApplicationCredentials(
        1411296422483155L,
        "6904665a1481e8b4ef1d0fca2f3b8673"
    );

    private CloseableHttpClient httpClient;
    private SimpleFacebookApiClient client;

    @BeforeClass
    public void beforeClass() {
        httpClient = HttpClients.createDefault();
        client = new SimpleFacebookApiClient(httpClient, CREDENTIALS);
    }

    @AfterClass
    public void afterClass() throws IOException {
        httpClient.close();
    }

    @Test
    public void getUrl() throws IOException {
        final GetUrlParameters parameters = new GetUrlParameters();
        parameters.setUrl(Long.toString(10150298925420108L));
        final GetUrlResponse response = client.getUrl(parameters);
        assertNotNull(response);
        assertNotNull(response.getDate());
        final Map<String, Object> data = response.getData();
        assertNotNull(data);
        assertEquals(data.get("id"), parameters.getUrl());
        assertEquals(data.get("type"), "video.movie");
        assertEquals(data.get("title"), "Guardians of the Galaxy (2014)");
    }
}
