package com.rra.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rra.base.engine.DataStreamer;
import com.rra.base.exception.ConsumeException;
import com.rra.base.exception.ProduceException;
import com.rra.base.exception.TransformException;
import com.rra.base.modal.RestStreamInputVO;
import com.rra.base.persistence.StreamStore;
import com.rra.base.persistence.repository.StreamStoreRepository;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
public class StreamController {

	private static Logger _logger = LoggerFactory.getLogger(StreamController.class);
	
	@Autowired
	private DataStreamer ds ;
	
	@Autowired
	private StreamStoreRepository sr;
	

	
	@PostMapping(value = "/process")
	@ResponseBody
	public ResponseEntity<String> process(@RequestBody RestStreamInputVO input) {
		Map<String, Object> resultVO = new HashMap<String, Object>();
		String status = "stream successfully processed";
		ds.setData(input.getData());
		ds.setOption(input.getOption());
		try {
			_logger.info("data = "+input.getData());
			ds.process();
		} catch (ConsumeException e) {
			_logger.error("Failed to process the stream", e);
			status = "Failed to process the stream because "+e.getMessage();
		} catch (TransformException e) {
			_logger.error("Failed to process the stream", e);
			status = "Failed to process the stream because "+e.getMessage();
		} catch (ProduceException e) {
			_logger.error("Failed to process the stream", e);
			status = "Failed to process the stream because "+e.getMessage();
		}
		
		resultVO.put("status", status);
		return new ResponseEntity(resultVO, getJsonHeader(), HttpStatus.OK);

	}
	
	@GetMapping(value = "/fetchstoredstream")
	@ResponseBody
	public ResponseEntity<String> getAllStreams() {	
		List<StreamStore> sList = sr.findAll();
		_logger.debug("sList = "+sList.size());
		List<HashMap<String, String>> r = new ArrayList<HashMap<String, String>>();
		for(StreamStore s : sList) {
			HashMap<String, String> h = new HashMap<String, String>();
			h.put("id", String.valueOf(s.getStreamStoreId()));
			h.put("produced_data", s.getData());
			h.put("created_date", s.getEnteredDate().toString());	
			r.add(h);
		}
		return new ResponseEntity(r, getJsonHeader(), HttpStatus.OK);
	}

	public HttpHeaders getJsonHeader() {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.APPLICATION_JSON);
		return resHeader;
	}

}
