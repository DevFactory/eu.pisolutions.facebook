package eu.pisolutions.facebook.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author Laurent Pireyn
 */
public class OpenGraphNodeTest {
    @Test
    public void nullValue() {
        final OpenGraphNode node = OpenGraphNode.value(null);
        assertNull(node.value());
        assertNull(node.valueOrNull(String.class));
        assertNull(node.field("abc").value());
    }

    @Test
    public void stringValue() {
        assertEquals(OpenGraphNode.value("abc").value(), "abc");
    }

    @Test
    public void stringField() {
        final Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        final OpenGraphNode node = OpenGraphNode.value(map);
        assertNull(node.field("x").value());
        assertEquals(node.field("key").value(), "value");
    }
}
