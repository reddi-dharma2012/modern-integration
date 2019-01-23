package com.sainsburys.adaptor.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sainsburys.adaptor.models.Shipment;

@Service
public class ShipmentServiceImpl implements ShipmentService {

	private static final Logger LOG = LoggerFactory.getLogger(ShipmentServiceImpl.class);

	public final String dateFormat = "dd/mm/yyyy";

	@Override
	public List<Shipment> processShipmentsFromFileServer(String filepath) {
		LOG.info("processShipmentsFromFileServer-filepath" + filepath);
		String line = "";
		String cvsSplitBy = ",";
		List<Shipment> shipments = new ArrayList<Shipment>();
		LOG.debug("filepath" + filepath);
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			while ((line = br.readLine()) != null) {
				Shipment shipment = new Shipment();
				String[] asn_info = line.split(cvsSplitBy);
				shipment.setShipmentId(asn_info[0]);
				shipment.setShipmentDate(asn_info[1]);
				shipment.setUpc(asn_info[2]);
				shipment.setDestinationLocation(asn_info[3]);
				shipment.setSendingLocation(asn_info[4]);
				shipment.setSoldTo(asn_info[3]);
				shipment.setOriginator(asn_info[4]);
				shipment.setQty(asn_info[5]);
				shipment.setLin(asn_info[6]);
				shipment.setPackSize(asn_info[7]);
				shipment.setDocumentId(asn_info[8]);
				shipments.add(shipment);
				LOG.debug("Shipment:" + shipment);
			}

		} catch (IOException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		return shipments;
	}

}
