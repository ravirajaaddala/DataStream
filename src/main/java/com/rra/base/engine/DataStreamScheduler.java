package com.rra.base.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.rra.base.exception.ConsumeException;
import com.rra.base.exception.ProduceException;
import com.rra.base.exception.TransformException;

@Configuration
@EnableScheduling
public class DataStreamScheduler {

	private static Logger _logger = LoggerFactory.getLogger(DataStreamScheduler.class);
	
	@Autowired
	DataStreamer ds ;
	
	//for every 3 mins
	@Scheduled(fixedDelay = 180000, initialDelay = 1000)
	public void cron() {
		_logger.info("cron called");
		try {
			ds.process();
		} catch (ConsumeException e) {
			ExceptionHandler eh =new ExceptionHandler();
			eh.handle(e);
		} catch (TransformException e) {
			ExceptionHandler eh =new ExceptionHandler();
			eh.handle(e);
		} catch (ProduceException e) {
			ExceptionHandler eh =new ExceptionHandler();
			eh.handle(e);
		}
	}
}
