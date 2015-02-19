package eu.pisolutions.facebook.api;

import java.io.IOException;

/**
 * {@link java.io.IOException} thrown by a {@link eu.pisolutions.facebook.api.FacebookApiClient}.
 *
 * @author Laurent Pireyn
 */
public class FacebookApiException extends IOException {
    private static final long serialVersionUID = 1L;

    private static String createMessage(FacebookApiError error) {
        return error != null ? error.getMessage() : "Undefined error";
    }

    private final FacebookApiError error;

    public FacebookApiException(FacebookApiError error) {
        super(createMessage(error));

        this.error = error;
    }

    public final FacebookApiError getError() {
        return error;
    }
}
