package eu.pisolutions.facebook.api;

import java.io.Serializable;

/**
 * Facebook API error.
 *
 * @author Laurent Pireyn
 */
public final class FacebookApiError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String type;
    private String message;
    private Integer errorSubcode;
    private String errorUserTitle;
    private String errorUserMessage;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorSubcode() {
        return errorSubcode;
    }

    public void setErrorSubcode(Integer errorSubcode) {
        this.errorSubcode = errorSubcode;
    }

    public String getErrorUserTitle() {
        return errorUserTitle;
    }

    public void setErrorUserTitle(String errorUserTitle) {
        this.errorUserTitle = errorUserTitle;
    }

    public String getErrorUserMessage() {
        return errorUserMessage;
    }

    public void setErrorUserMessage(String errorUserMessage) {
        this.errorUserMessage = errorUserMessage;
    }

    @Override
    public String toString() {
        return "Facebook API error (code: " + code + ", type: " + type + ", message: " + message + ')';
    }
}
