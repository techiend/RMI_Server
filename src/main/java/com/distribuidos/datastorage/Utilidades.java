package com.distribuidos.datastorage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilidades {
    public static String generateResponse(Object data){
        ObjectMapper mapper = new ObjectMapper();
        String respuesta = null;
        try {
            respuesta = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
