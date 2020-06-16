package fr.tcd.server.utils.http_entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpEntityUtils {
    public static HttpEntity getHttpEntity(String url) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        int responseCode = response.getStatusLine().getStatusCode();
        if(responseCode > 299) {
            throw new GetEntityException(String.valueOf(responseCode));
        }
        return response.getEntity();
    }


}
