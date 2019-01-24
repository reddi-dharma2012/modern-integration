package com.sainsburys.translator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sainsburys.translator.utility.conversion.StringToSqlDateConverter;
import com.sainsburys.translator.controller.TranslatorController;
import com.sainsburys.translator.lookupservice.ItemLookupDao;
import com.sainsburys.translator.models.Item;
import com.sainsburys.translator.models.Shipment;
import com.sainsburys.translator.models.ShipmentInfo;
import com.sainsburys.translator.restapiutil.RestAPIUtil;

@Service
public class TranslateShipmentInfoServiceImpl implements TranslateShipmentInfoService{
	private static final Logger LOG = LoggerFactory.getLogger(TranslatorController.class);
	@Autowired
	public RestAPIUtil restAPIUtil;
	@Autowired
	public ItemLookupDao itemLookupDao;
	
	public void postShipments(Shipment shipment) {
		ShipmentInfo shipmentInfo = tranlateShipmentInfo(shipment);
		LOG.info("TranslateShipmentInfoServiceImpl- postShipments- shipmentInfo:"+shipmentInfo);
		restAPIUtil.postShipments(shipmentInfo);
		
	}
	public ShipmentInfo tranlateShipmentInfo(Shipment shipment) {
		LOG.info("Translating the shipment data" + shipment);
		ShipmentInfo shipmentInfo = new ShipmentInfo();
		shipmentInfo.setShipmentNumber(shipment.getShipmentId());
		shipmentInfo.setShipmentDate(new StringToSqlDateConverter("ddmmyyyy").convert(shipment.getShipmentDate()));
		shipmentInfo.setSku(getsku(shipment.getUpc()));
		shipmentInfo.setDestinationLocation(shipment.getDestinationLocation());
		shipmentInfo.setSendingLocation(shipment.getSendingLocation());
		shipmentInfo.setSoldTo(shipment.getSoldTo());
		shipmentInfo.setOriginator(shipment.getOriginator());
		shipmentInfo.setQty(Double.parseDouble(shipment.getQty()));
		shipmentInfo.setUom(shipment.getLin());
		shipmentInfo.setPackSize(shipment.getPackSize());
		shipmentInfo.setDocumentId(shipment.getDocumentId());
		return shipmentInfo;
	}
	
	public String getsku(String upc) {
		LOG.info("Getting SKU from the item lookup service on the basis of given UPC" + upc);
		Item item = itemLookupDao.getItemDetails(Integer.valueOf(upc));
		if(item!=null) {
			return Integer.toString(item.getSku());
		}else{
			return "1111";
		}
		
	}
	
}
