package com.sainsburys.adaptor.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sainsburys.adaptor.service.AdaptorServiceImpl;

@RestController
public class AdopterController {

	private static final Logger LOG = LoggerFactory.getLogger(AdopterController.class);

	@Autowired
	public AdaptorServiceImpl adaptorServiceImpl;

	@RequestMapping(value = "/shipments", method = RequestMethod.GET)
	public String processShipmentsFrommFileServer() {
		LOG.info("Entering-processShipments");
		adaptorServiceImpl.pollShipmentsFromFileServer();
		return "OK";

	}
}
