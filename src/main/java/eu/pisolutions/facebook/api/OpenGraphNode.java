package eu.pisolutions.facebook.api;

import java.util.Map;

/**
 * OpenGraph node.
 *
 * @author Laurent Pireyn
 */
public final class OpenGraphNode {
    private static final OpenGraphNode NULL_VALUE_INSTANCE = new OpenGraphNode(null);

    public static OpenGraphNode value(Object value) {
        return value != null ? new OpenGraphNode(value) : NULL_VALUE_INSTANCE;
    }

    private final Object value;

    private OpenGraphNode(Object value) {
        this.value = value;
    }

    public Object value() {
        return value;
    }

    public <T> T valueOrNull(Class<T> clazz) {
        return value != null && clazz.isAssignableFrom(value.getClass()) ?
            clazz.cast(value) :
            null;
    }

    public OpenGraphNode field(String name) {
        @SuppressWarnings("unchecked")
        final Map<?, ?> map = valueOrNull(Map.class);
        return value(map != null ? map.get(name) : null);
    }
}
