package com.sainsburys.translator.models;

import java.io.Serializable;
import java.util.Date;
public class ShipmentInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String shipmentId;
	private String documentId;
	private Date shipmentDate;
    private String destinationLocation;
	private String  sendingLocation;
	private double qty;
	private String packSize;
	private String soldTo;
	private String originator;
	private String uom;
	private String sku;
	
	public String getUom() {
		return uom;
	}
	public void setUom(String lin) {
		
		if(lin.contentEquals("EN"))
		 this.uom = "Cases";
		else if (lin.contentEquals("UP")) {
			this.uom = "Units";
		}
	}
	public String getshipmentId() {
		return shipmentId;
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentId = shipmentNumber;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public Date getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	
 
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		if(sku != null && !sku.isEmpty()) {
			this.sku = sku;
		}
		else
			this.sku = "987654";
		
	}
	public String getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	public String getSendingLocation() {
		return sendingLocation;
	}
	public void setSendingLocation(String sendingLocation) {
		this.sendingLocation = sendingLocation;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public String getPackSize() {
		return packSize;
	}
	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}
	public String getSoldTo() {
		return soldTo;
	}
	public void setSoldTo(String soldTo) {
		this.soldTo = soldTo;
	}
	public String getOriginator() {
		return originator;
	}
	public void setOriginator(String originator) {
		this.originator = originator;
	}
	@Override
	public String toString() {
		return "ShipmentInfo [shipmentId=" + shipmentId + ", documentId=" + documentId + ", shipmentDate="
				+ shipmentDate + ", destinationLocation=" + destinationLocation + ", sendingLocation=" + sendingLocation
				+ ", qty=" + qty + ", packSize=" + packSize + ", soldTo=" + soldTo + ", originator=" + originator
				+ ", uom=" + uom + ", sku=" + sku + "]";
	}
	
}
