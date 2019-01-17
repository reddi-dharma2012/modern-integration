package com.sainsburys.facade.service;

import com.sainsburys.facade.models.ShipmentInfo;

public interface PublisherService {
	public void postShipmentsToMessageBus(ShipmentInfo shipmentInfo);

}
