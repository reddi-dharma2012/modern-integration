package com.sainsburys.translator.restapiutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sainsburys.translator.controller.TranslatorController;
import com.sainsburys.translator.models.ShipmentInfo;


@Component
public class RestAPIUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(RestAPIUtil.class);
	@Value("${facade.api.url}")
	private String facadeApiUrl;
	public void postShipments(ShipmentInfo shipmentInfo) {
		LOG.info("Invoke Facade module for shipmentInfo: "+shipmentInfo);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		// Data attached to the request.
		HttpEntity<ShipmentInfo> requestBody = new HttpEntity<>(shipmentInfo);
		// Send request with POST method.
		try {
			LOG.info("Invoking Facde Rest API facadeApiUrl: "+facadeApiUrl);
			response = restTemplate.postForEntity(facadeApiUrl, requestBody, String.class);
		} catch (RestClientException exp) {
			LOG.error("Error while calling Facade Rest API" +exp.getMessage());
			exp.printStackTrace();
		}
		LOG.info("Response Status code:" + response.getStatusCode() + "for shipmentInfo" + shipmentInfo);

		// Code = 200.
		if (response.getStatusCode() == HttpStatus.OK) {

			LOG.info(response.getBody());
		}

	}

}
