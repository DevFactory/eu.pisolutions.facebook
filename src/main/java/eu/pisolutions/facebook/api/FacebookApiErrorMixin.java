package eu.pisolutions.facebook.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mix-in for {@link eu.pisolutions.facebook.api.FacebookApiError}.
 *
 * @author Laurent Pireyn
 */
abstract class FacebookApiErrorMixin {
    @JsonProperty("error_user_msg")
    abstract String setErrorUserMessage();
}
