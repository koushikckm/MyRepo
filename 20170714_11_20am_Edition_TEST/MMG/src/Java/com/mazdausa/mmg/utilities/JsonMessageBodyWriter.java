/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.utilities;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * This Utility will be responsible for writing the Object to JSON by using.
 * @author PankajB
 * http://stackoverflow.com/questions/2199453/how-can-i-customize-serialization-of-a-list-of-jaxb-objects-to-json
 * @version 1.0
 */
@Provider
@Produces("application/json")
public class JsonMessageBodyWriter implements MessageBodyWriter {

    static ObjectMapper mapper = new ObjectMapper();

    static
    {
        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }

    
    @Override
    public long getSize(Object obj, Class type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class type, Type genericType,
            Annotation annotations[], MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object target, Class type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap httpHeaders, OutputStream outputStream)
            throws IOException {
        /*
        if (target != null)
             new ObjectMapper().writeValue(outputStream, target); */
             mapper.writeValue(outputStream, target); 

        
        
    }
}
