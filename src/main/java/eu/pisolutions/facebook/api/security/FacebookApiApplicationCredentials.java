package eu.pisolutions.facebook.api.security;

import static java.util.Objects.requireNonNull;

/**
 * Application {@link eu.pisolutions.facebook.api.security.FacebookApiCredentials}.
 *
 * @author Laurent Pireyn
 */
public final class FacebookApiApplicationCredentials extends FacebookApiCredentials {
    private static final long serialVersionUID = 1L;

    private final long id;
    private final String secret;

    public FacebookApiApplicationCredentials(long id, String secret) {
        this.id = id;
        this.secret = requireNonNull(secret);
    }

    public long getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }
}
