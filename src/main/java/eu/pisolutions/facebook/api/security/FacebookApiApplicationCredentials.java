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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof FacebookApiApplicationCredentials)) {
            return false;
        }
        final FacebookApiApplicationCredentials other = (FacebookApiApplicationCredentials) object;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public String toString() {
        return "Facebook API application credentials (ID: " + id + ')';
    }
}
