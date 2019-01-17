package com.sainsburys.facade.utility;

import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsburys.facade.models.*;
import com.sainsburys.facade.service.PublisherServiceImpl;

public class ShipmentInfoSerializer implements Serializer<ShipmentInfo> {
    
	private static final Logger LOG = LoggerFactory.getLogger(ShipmentInfoSerializer.class);
	
	@Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
    
    @Override
    public byte[] serialize(String topic, ShipmentInfo data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception) {
        	LOG.error("Error in serializing object"+ exception.getMessage());
        }
        return retVal;
    }
    @Override
    public void close() {
    }
}