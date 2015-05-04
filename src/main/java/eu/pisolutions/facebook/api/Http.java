package eu.pisolutions.facebook.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.JavaType;

/**
 * HTTP utilities.
 *
 * @author Laurent Pireyn
 */
final class Http {
    static List<NameValuePair> toNameValuePairs(Map<String, String> map) {
        final List<NameValuePair> list = new ArrayList<>(map.size());
        for (final Entry<String, String> entry : map.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    static Date toDate(Header header) {
        return header != null ? DateUtils.parseDate(header.getValue()) : null;
    }

    static Date getDate(HttpResponse response) {
        return toDate(response.getFirstHeader(HttpHeaders.DATE));
    }

    static <T> T readValue(HttpEntity entity, JavaType type) throws IOException {
        if (entity == null) {
            return null;
        }
        try (final InputStream content = entity.getContent()) {
            return Json.readValue(content, type);
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
    }

    private Http() {}
}
