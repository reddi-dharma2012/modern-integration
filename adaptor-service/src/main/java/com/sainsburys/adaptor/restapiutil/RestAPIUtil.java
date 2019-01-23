package com.sainsburys.adaptor.restapiutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.sainsburys.adaptor.models.Shipment;

@Component
public class RestAPIUtil {
	private static final Logger LOG = LoggerFactory.getLogger(RestAPIUtil.class);

	@Value("${shipment.api.url}")
	private String apiUrl;

	public void postShipments(Shipment shipmentInfo) {
		LOG.info("postShipments-shipmentInfo" + shipmentInfo);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		// Data attached to the request.
		HttpEntity<Shipment> requestBody = new HttpEntity<>(shipmentInfo);
		// Send request with POST method.
		try {
			LOG.info("apiUrl: " + apiUrl);
			LOG.info("Invoking Rest API call -: " + apiUrl);
			response = restTemplate.postForEntity(apiUrl, requestBody, String.class);
		} catch (RestClientException exp) {
			LOG.error("Exception Occured" + exp);
			LOG.error(exp.getMessage());
			exp.printStackTrace();
		}
		LOG.debug("Response Status code:" + response.getStatusCode());
		if (response.getStatusCode() == HttpStatus.OK) {
			LOG.debug(response.getBody());
		}

	}

}
