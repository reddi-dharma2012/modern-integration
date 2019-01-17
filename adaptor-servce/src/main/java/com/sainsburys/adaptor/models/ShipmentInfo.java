package com.sainsburys.adaptor.models;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shipments")
public class ShipmentInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String shipmentId;
	private String documentId;
	private String shipmentDate;
    private String destinationLocation;
	private String  sendingLocation;
	private double qty;
	private String packSize;
	private String soldTo;
	private String originator;
	private String lin;
	private String upc;
	
	
	public String getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getLin() {
		return lin;
	}
	public void setLin(String lin) {
		this.lin = lin;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
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
	public String getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
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
				+ ", lin=" + lin + ", upc=" + upc + "]";
	}
	
}
