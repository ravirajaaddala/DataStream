package com.rra.base.produce;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.rra.base.modal.OutputDataVO;
import com.rra.base.persistence.StreamStore;
import com.rra.base.persistence.repository.StreamStoreRepository;

////to save the output in to database
@Component("database")
public class DatabaseProducer implements ProducerIntf {

	private static Logger _logger = LoggerFactory.getLogger(DatabaseProducer.class);
	
	@Autowired
	private StreamStoreRepository sr;
	
	@Override
	public void write(List<OutputDataVO> data) {
		StreamStore s = new StreamStore();
		Gson gson = new Gson();
		String jsonData = gson.toJson(data);
		s.setData(jsonData);
		s.setEnteredDate(new Date());
		sr.save(s);
		_logger.info("data saved to db ");
	}

}
