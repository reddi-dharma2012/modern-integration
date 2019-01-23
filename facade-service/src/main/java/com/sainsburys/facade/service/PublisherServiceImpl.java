package com.sainsburys.facade.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sainsburys.facade.models.ShipmentInfo;

import com.sainsburys.facade.utility.ShipmentInfoSerializer;


@Service
public class PublisherServiceImpl implements PublisherService{
	private static final Logger LOG = LoggerFactory.getLogger(PublisherServiceImpl.class);
	
	@Value("${topic.name}")
	private String topic;
	@Value("${integration.bootstrap.servers}")
	private String bootstrapServer;
	@Value("${ack.config}")
	private String ackConfig;
	@Value("${retry.config}")
	private String retryConfig;
	@Value("${key.string.serializer}")
	private String keySerializer;
	

	public void postShipmentsToMessageBus(ShipmentInfo shipmentInfo) {
		LOG.debug("postShipmentsToMessageBus-shipmentInfo : "+shipmentInfo);
		LOG.debug("topic : "+topic);
		LOG.debug("bootstrapServer : "+bootstrapServer);
		LOG.debug("ackConfig : "+ackConfig);
		LOG.debug("retryConfig : "+retryConfig);
		LOG.debug("keySerializer : "+keySerializer);
		
		Producer<String, ShipmentInfo> producer = null;
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ProducerConfig.ACKS_CONFIG, ackConfig);
		props.put(ProducerConfig.RETRIES_CONFIG, retryConfig);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ShipmentInfoSerializer.class.getName());
		try {
			producer = new KafkaProducer<String, ShipmentInfo>(props);
			ProducerCallback callback = new ProducerCallback();
			ProducerRecord<String, ShipmentInfo> data = new ProducerRecord<String, ShipmentInfo>(topic, "KEY", shipmentInfo);
			producer.send(data, callback);
		} catch (Exception exp) {
			LOG.error(exp.getMessage());
			} finally {
			if (producer != null)
				producer.close();
		}
		
	}
}

class ProducerCallback implements Callback {
	private static final Logger LOG = LoggerFactory.getLogger(ProducerCallback.class);
	@Override
	public void onCompletion(RecordMetadata recordMetadata, Exception e) {
		if (e != null) {
			LOG.error("Error while producing message to topic :" + recordMetadata);
			e.printStackTrace();
		} else {
			String message = String.format("sent message to topic:%s partition:%s  offset:%s", recordMetadata.topic(),
					recordMetadata.partition(), recordMetadata.offset());
			LOG.error(message);
		}
	}

}
