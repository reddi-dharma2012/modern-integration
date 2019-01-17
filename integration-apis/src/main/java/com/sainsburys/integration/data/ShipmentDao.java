package com.sainsburys.integration.data;
import org.springframework.data.repository.CrudRepository;
import com.sainsburys.integration.models.Shipment;

public interface ShipmentDao extends CrudRepository<Shipment, String> {
    
}