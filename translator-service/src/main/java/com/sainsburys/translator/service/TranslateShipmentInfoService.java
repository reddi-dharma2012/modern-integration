package com.sainsburys.translator.service;

import com.sainsburys.translator.models.Shipment;
import com.sainsburys.translator.models.ShipmentInfo;

public interface TranslateShipmentInfoService {
	public ShipmentInfo tranlateShipmentInfo(Shipment shipment);
	public void postShipments(Shipment shipment);
	
}
