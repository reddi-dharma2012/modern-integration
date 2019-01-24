package com.sainsburys.facade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sainsburys.facade.models.ShipmentInfo;
import com.sainsburys.facade.service.PublisherService;


@RestController
public class FacadeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(FacadeController.class);
	
	@Autowired
	public PublisherService publisherService;
	
	@RequestMapping(value = "/shipment", method = RequestMethod.POST)
	public String getShipment(@RequestBody ShipmentInfo shipment) {
		LOG.info("Entering FacadeController for shipmentInfo" + shipment);
		publisherService.postShipmentsToMessageBus(shipment);
		return "OK";

	}
}
