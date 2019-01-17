package com.sainsburys.facade.restapiutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sainsburys.facade.models.ShipmentInfo;
import com.sainsburys.facade.service.PublisherServiceImpl;

@Component
public class RestAPIUtil {

	private static final Logger LOG = LoggerFactory.getLogger(PublisherServiceImpl.class);
	@Value("${shipment.api.url}")
	private String apiUrl;

	public void postShipments(ShipmentInfo shipmentInfo) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ShipmentInfo> response = null;
		// Data attached to the request.
		HttpEntity<ShipmentInfo> requestBody = new HttpEntity<>(shipmentInfo);
		// Send request with POST method.
		try {
			response = restTemplate.postForEntity(apiUrl, requestBody, ShipmentInfo.class);
		} catch (RestClientException exp) {
			LOG.error(exp.getMessage());
			exp.printStackTrace();
		}
		LOG.debug("Response Status code:" + response.getStatusCode());

		// Code = 200.
		if (response.getStatusCode() == HttpStatus.OK) {
			LOG.debug("Response:" + response.getBody());
		}

	}

}
