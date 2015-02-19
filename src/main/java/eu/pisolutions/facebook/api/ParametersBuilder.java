package eu.pisolutions.facebook.api;

import java.util.HashMap;
import java.util.Map;

import eu.pisolutions.Builder;

/**
 * Parameters builder.
 *
 * @author Laurent Pireyn
 */
final class ParametersBuilder implements Builder<Map<String, String>> {
    private final Map<String, String> parameters = new HashMap<>();

    ParametersBuilder set(String name, Object value) {
        if (value != null) {
            parameters.put(name, value.toString());
        }
        return this;
    }

    @Override
    public Map<String, String> build() {
        return parameters;
    }
}
