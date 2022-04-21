package com.rra.base.consume;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.rra.base.exception.ConsumeException;
import com.rra.base.helper.FileHelper;
import com.rra.base.modal.InputDataVO;


public class FileReaderConsumer implements ConsumerIntf {

	//private static Logger _logger = LoggerFactory.getLogger(FileReaderConsumer.class);
	
	private static HashMap<String,Long> _fileAccessStamps = new HashMap<String,Long>();
	
	@Override
	public List<InputDataVO> read() throws ConsumeException {
		List<InputDataVO> dataList = new ArrayList<InputDataVO>();
		String data = null;
		try {
			List<File> filesInFolder = Files.walk(Paths.get("externaldata"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
			for(File f : filesInFolder) {
				Long lastModifiedTime = _fileAccessStamps.get(f.getName());
				//_logger.info("lastModifiedTime for "+f.getName()+" from map is "+lastModifiedTime
				//		+" ,from system is "+f.lastModified());
				if(lastModifiedTime!=null) { // already accessed once
					Long lastModified = _fileAccessStamps.get(f.getName());
					if(lastModified == f.lastModified()) { // no change
						continue;
					}
				}
				_fileAccessStamps.put(f.getName(), f.lastModified()); // file accessed for the first time or updating the last modified time
				data = FileHelper.getData(f);
				dataList.add(getJsonData(data));
			}
			//_logger.info("dataList = "+dataList.toString());
		} catch (Exception e) {
			throw new ConsumeException("Not able to read/find the file", e);
		}
		return dataList;
	}

	@Override
	public InputDataVO read(String data) throws ConsumeException{
		return getJsonData(data);
	}

	private InputDataVO getJsonData(String data) throws ConsumeException {
		try {
			Gson gson = new Gson();
			return  gson.fromJson(data, InputDataVO.class);
		}catch(Exception e) {
			throw new ConsumeException("the data of the input is not as per the requirement. Its not parseable", e);
		}
	}
	

	
}
