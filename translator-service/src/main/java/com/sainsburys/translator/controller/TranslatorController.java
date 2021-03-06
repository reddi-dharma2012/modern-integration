package com.sainsburys.translator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sainsburys.translator.lookupservice.ItemLookupDao;
import com.sainsburys.translator.models.Item;
import com.sainsburys.translator.models.Shipment;
import com.sainsburys.translator.service.TranslateShipmentInfoService;

@RestController
public class TranslatorController {
	private static final Logger LOG = LoggerFactory.getLogger(TranslatorController.class);
	@Autowired
	private TranslateShipmentInfoService translateShipmentInfoService;
	
	@Autowired
	private ItemLookupDao itemLookupDao;
	
	@RequestMapping(value = "/shipment", method = RequestMethod.POST)
	public String getShipment(@RequestBody Shipment shipment) {
		LOG.info("Entering Translater Controller"+shipment);
		LOG.info("shipment:"+shipment);
		translateShipmentInfoService.postShipments(shipment);
		return "OK";

	}
	
	@RequestMapping(value = "/item/{upc}", method = RequestMethod.GET)
	public Item getItem(@PathVariable("upc") int upc) {
		LOG.info("Get Item for UPC:"+upc);
		return itemLookupDao.getItemDetails(upc);
	}
	
}
