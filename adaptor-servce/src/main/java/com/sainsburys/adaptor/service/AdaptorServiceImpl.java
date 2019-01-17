package com.sainsburys.adaptor.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sainsburys.adaptor.models.Shipment;
import com.sainsburys.adaptor.models.ShipmentInfo;
import com.sainsburys.adaptor.restapiutil.RestAPIUtil;

@Component
public class AdaptorServiceImpl implements InitializingBean {
	private static final Logger LOG = LoggerFactory.getLogger(AdaptorServiceImpl.class);
	@Autowired
	public ShipmentService shipmentService;
	@Autowired
	public com.sainsburys.adaptor.restapiutil.RestAPIUtil RestAPIUtil;
	@Value("${fileserver.path}")
	private String fileServer;

	private WatchKey key;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(13);
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				pollShipmentsFromFileServer();
			}
		}, 0, 5, TimeUnit.SECONDS);
	}

	public void pollShipmentsFromFileServer() {
		
		Path path =null;
		WatchService watchService = null;
		try {
			path=Paths.get(fileServer);
			watchService = FileSystems.getDefault().newWatchService();
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		try {
			while ((key = watchService.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					LOG.debug("Event" + event.kind() + ". File affected: " + event.context() + ".");
					System.out.println(StandardWatchEventKinds.ENTRY_CREATE);
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						LOG.debug("Event" + event.kind());
						List<Shipment> shipments = shipmentService
								.processShipmentsFromFileServer(fileServer + "/" + event.context());
						for (Shipment shipmentInfo : shipments) {
							LOG.debug("Adaptor-Invoke REST API");
							RestAPIUtil.postShipments(shipmentInfo);
						}
					}
				}
				key.reset();
			}
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
			
		}catch (Exception e) {
			LOG.error(e.getMessage());
			
		}
	}
	
}