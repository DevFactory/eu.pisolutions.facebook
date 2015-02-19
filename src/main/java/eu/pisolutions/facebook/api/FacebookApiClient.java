package eu.pisolutions.facebook.api;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Facebook API client.
 *
 * @author Laurent Pireyn
 */
public interface FacebookApiClient {
    abstract class Parameters implements Serializable {
        private static final long serialVersionUID = 1L;
    }

    abstract class Response implements Serializable {
        private static final long serialVersionUID = 1L;

        private Date date;
        private Map<String, Object> data;

        public final Date getDate() {
            return date;
        }

        public final void setDate(Date date) {
            this.date = date;
        }

        public Map<String, Object> getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }
    }

    final class GetUrlParameters extends Parameters {
        private static final long serialVersionUID = 1L;

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    final class GetUrlResponse extends Response {
        private static final long serialVersionUID = 1L;
    }

    GetUrlResponse getUrl(GetUrlParameters parameters) throws IOException;
}
