package io.weli;

import org.codehaus.jettison.mapped.DefaultConverter;

public class MappedNamespaceConvention_convertToJSONPrimitive_method_demo {
    public static void main(String[] args) {
        DefaultConverter converter = new DefaultConverter();

        Object obj = converter.convertToJSONPrimitive("{\"customer\":{\"@id\":\"1\",\"prop.name\":\"Tom\"}}");

        System.out.println(obj.getClass());
        System.out.println(converter.convertToJSONPrimitive("1").getClass());
    }
}
