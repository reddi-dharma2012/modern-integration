package com.sainsburys.translator.restapiutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sainsburys.translator.models.ShipmentInfo;


@Component
public class RestAPIUtil {
	@Value("${api.url}")
	private String apiUrl;
	public void postShipments(ShipmentInfo shipmentInfo) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		// Data attached to the request.
		HttpEntity<ShipmentInfo> requestBody = new HttpEntity<>(shipmentInfo);
		// Send request with POST method.
		try {
			response = restTemplate.postForEntity(apiUrl, requestBody, String.class);
		} catch (RestClientException exp) {
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
		System.out.println("Response Status code:" + response.getStatusCode());

		// Code = 200.
		if (response.getStatusCode() == HttpStatus.OK) {

			System.out.println(response.getBody());
		}

	}

}
