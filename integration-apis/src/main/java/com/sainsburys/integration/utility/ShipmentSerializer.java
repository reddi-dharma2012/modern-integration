package com.sainsburys.integration.utility;

import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsburys.integration.ApplicationInitializer;
import com.sainsburys.integration.models.*;

public class ShipmentSerializer implements Serializer<Shipment> {
	private static Logger log = LogManager.getLogger(ApplicationInitializer.class);
	
	@Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
    
    @Override
    public byte[] serialize(String topic, Shipment data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception) {
        	log.error("Error in serializing object"+ exception.getMessage());
        }
        return retVal;
    }
    @Override
    public void close() {
    }
}