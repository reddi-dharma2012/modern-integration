package com.sainsburys.integration.data;

import org.springframework.data.repository.CrudRepository;
import com.sainsburys.integration.models.ShipmentInfo;

public interface ShipmentInfoDao extends CrudRepository<ShipmentInfo, String> {
    
}