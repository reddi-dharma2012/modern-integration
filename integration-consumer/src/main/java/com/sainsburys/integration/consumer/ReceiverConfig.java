package com.sainsburys.integration.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.sainsburys.integration.models.ShipmentInfo;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ReceiverConfig {

    @Value("${integration.bootstrap.servers}")
    private String bootstrapServers;
    @Value("${consumer.group.id}")
    private String consumerGroup;
    

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        return props;
    }

    @Bean
    public ConsumerFactory<String, ShipmentInfo> consumerFactory() {
      return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
          new JsonDeserializer<>(ShipmentInfo.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShipmentInfo> kafkaListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String, ShipmentInfo> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(consumerFactory());

      return factory;
    }
 
}
