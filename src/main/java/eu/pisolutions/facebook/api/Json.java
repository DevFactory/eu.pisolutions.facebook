package eu.pisolutions.facebook.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * JSON utilities.
 *
 * @author Laurent Pireyn
 */
final class Json {
    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    static final JavaType FACEBOOK_API_ERROR_TYPE = createJavaType(FacebookApiError.class);
    static final JavaType DATA_TYPE = createJavaType(new TypeReference<Map<String, Object>>() {});

    private static ObjectMapper createObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper()
            // Use setters only
            .disable(MapperFeature.AUTO_DETECT_FIELDS)
            .disable(MapperFeature.AUTO_DETECT_CREATORS)
            .disable(MapperFeature.USE_GETTERS_AS_SETTERS)
            // Setters are public
            .disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)
            .disable(MapperFeature.INFER_PROPERTY_MUTATORS)
            // Use annotations (on mix-ins)
            .enable(MapperFeature.USE_ANNOTATIONS)
            // Ignore unknown properties
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            // Property naming strategy
            .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
        ;
        // Mix-ins
        objectMapper.addMixInAnnotations(FacebookApiError.class, FacebookApiErrorMixin.class);
        return objectMapper;
    }

    static TypeFactory getTypeFactory() {
        return OBJECT_MAPPER.getTypeFactory();
    }

    static JavaType createJavaType(Class<?> clazz) {
        return getTypeFactory().constructType(clazz);
    }

    static JavaType createJavaType(TypeReference<?> typeRef) {
        return getTypeFactory().constructType(typeRef);
    }

    static <T> T readValue(InputStream input, JavaType type) throws IOException {
        return OBJECT_MAPPER.readValue(input, type);
    }

    private Json() {}
}
