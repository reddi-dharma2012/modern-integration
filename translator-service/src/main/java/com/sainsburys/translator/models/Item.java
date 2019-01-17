package com.sainsburys.translator.models;

import java.io.Serializable;

public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int sku;
	private int upc;
	private String skuDesc;
	
	public int getSku() {
		return sku;
	}
	public void setSku(int sku) {
		this.sku = sku;
	}
	public int getUpc() {
		return upc;
	}
	public void setUpc(int upc) {
		this.upc = upc;
	}
	public String getSkuDesc() {
		return skuDesc;
	}
	public void setSkuDesc(String skuDesc) {
		this.skuDesc = skuDesc;
	}
	@Override
	public String toString() {
		return "Item [sku=" + sku + ", upc=" + upc + ", skuDesc=" + skuDesc + "]";
	}
	

}
