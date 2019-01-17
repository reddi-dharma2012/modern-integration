package com.sainsburys.adaptor.service;

import java.util.List;

import com.sainsburys.adaptor.models.Shipment;


public interface ShipmentService {
	public List<Shipment> processShipmentsFromFileServer(String filepath);
}
