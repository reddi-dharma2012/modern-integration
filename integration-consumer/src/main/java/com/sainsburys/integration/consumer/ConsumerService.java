package com.sainsburys.integration.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.sainsburys.integration.data.ShipmentInfoDao;

import com.sainsburys.integration.models.ShipmentInfo;

@Service
public class ConsumerService {

	private static final Logger LOG = LoggerFactory.getLogger(ConsumerService.class);
	@Autowired
	public ShipmentInfoDao shipmentRepositoryService;
	
	@KafkaListener(topics = "test")
	public void listen(@Payload ShipmentInfo supplierAdviseMessage) {
		LOG.info("received message='{}'", supplierAdviseMessage);
		CrudRepository<ShipmentInfo, String> crudRepository = (CrudRepository<ShipmentInfo, String>) shipmentRepositoryService;
		ShipmentInfo message = crudRepository.save(supplierAdviseMessage);
		LOG.info("No sql persisted message='{}'", message);

	}
}
