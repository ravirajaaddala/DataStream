package com.rra.base.engine;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rra.base.consume.ConsumerIntf;
import com.rra.base.consume.FileReaderConsumer;
import com.rra.base.enums.UserOptionsEnum;
import com.rra.base.exception.ConsumeException;
import com.rra.base.exception.ProduceException;
import com.rra.base.exception.TransformException;
import com.rra.base.modal.InputDataVO;
import com.rra.base.modal.OutputDataVO;
import com.rra.base.produce.ProducerIntf;
import com.rra.base.transform.EmailTallyTransformer;
import com.rra.base.transform.TransformIntf;

@Service
public class DataStreamer {

	private static Logger _logger = LoggerFactory.getLogger(DataStreamer.class);
	
	/*
	 * public static void main(String[] args) { DataStreamer ds = new
	 * DataStreamer(); ds.process();
	 * 
	 * }
	 */
	@Autowired
	@Qualifier("email")
	private ProducerIntf emailProducer;

	@Autowired
	@Qualifier("database")
	private ProducerIntf databaseProducer;
	
	@Autowired
	@Qualifier("txt")
	private ProducerIntf filerWiterProducer;
	
	@Autowired
	@Qualifier("rest")
	private ProducerIntf restProducer;
	private String data = null;
	private String option = null;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	public void process() throws ConsumeException, TransformException, ProduceException {
		ConsumerIntf c = new FileReaderConsumer();
		String data = getData();
		List<InputDataVO> iList = null;
		if(data==null) {
			iList = c.read();
		}else {
			InputDataVO d = c.read(data);
			_logger.info("adding for "+d.getId());
			iList = new ArrayList<InputDataVO>();
			iList.add(d);
		}
		if(iList == null || iList.isEmpty()) {
			return;
		}
		List<OutputDataVO> transformedData = transform(iList);
		produce(transformedData);
	}

	
	private void produce(List<OutputDataVO> transformedData) throws  ProduceException {
		//_logger.info("transformedData = "+transformedData);
		ProducerIntf p = getProducer();
		p.write(transformedData);
	}
	private synchronized List<OutputDataVO> transform(List<InputDataVO> iList) throws TransformException {
		TransformIntf t = new EmailTallyTransformer();
		t.convert(iList);
		return t.getTransformedData();
		
	}
	private ProducerIntf getProducer() {
		if(UserOptionsEnum.DATABASE.getName().equals(getOption())) {
			return databaseProducer;
		}else if(UserOptionsEnum.EMAIL.getName().equals(getOption())) {
			return emailProducer;
		}else if(UserOptionsEnum.REST.getName().equals(getOption())) {
			return restProducer;
		}
		return filerWiterProducer;
	}
	
	
}
