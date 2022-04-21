package com.rra.base.produce;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.rra.base.exception.ProduceException;
import com.rra.base.helper.FileHelper;
import com.rra.base.modal.OutputDataVO;

@Component("txt")
public class FileWriterProducer implements ProducerIntf{
	
	private static Logger _logger = LoggerFactory.getLogger(FileWriterProducer.class);
	
	@Override
	public void write(List<OutputDataVO> data) throws ProduceException {
		Date d = new Date();
		String fileName = "outputdata/filteredLogFiles_"+d.getTime()+".json";
		try {
			_logger.info("generating "+fileName);
			Gson gson = new Gson();
			String jsonData = gson.toJson(data);
			FileHelper.addData(jsonData, fileName);
		} catch (Exception e) {
			throw new ProduceException(" not able to access the output file ",e);
		}
	}
}
