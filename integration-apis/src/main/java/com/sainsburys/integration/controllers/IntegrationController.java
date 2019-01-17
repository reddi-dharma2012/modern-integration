package com.sainsburys.integration.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sainsburys.integration.data.ShipmentDao;

import com.sainsburys.integration.models.Shipment;


@RestController
public class IntegrationController {
	private static final Logger log = LoggerFactory.getLogger(IntegrationController.class);

	@Autowired
	public ShipmentDao shipmentRepositoryService;

	@RequestMapping(value = "/getShipments", method = RequestMethod.GET)
	public Iterable<Shipment> getShipments() {
		CrudRepository<Shipment, String> crudRepository = (CrudRepository<Shipment, String>) shipmentRepositoryService;
		return crudRepository.findAll();
	}
	
	@RequestMapping(value = "/getShipments/{id}", method = RequestMethod.GET)
	public Optional<Shipment> getShipmentById(@PathVariable String id) {
		CrudRepository<Shipment, String> crudRepository = (CrudRepository<Shipment, String>) shipmentRepositoryService;
		return crudRepository.findById(id);
	}
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String test() {
		return "OK";
	}
}
