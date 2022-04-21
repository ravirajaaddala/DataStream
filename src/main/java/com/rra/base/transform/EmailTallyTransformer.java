package com.rra.base.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rra.base.exception.TransformException;
import com.rra.base.modal.InputDataVO;
import com.rra.base.modal.LogVO;
import com.rra.base.modal.OutputDataVO;
import com.rra.base.modal.TallyVO;

public class EmailTallyTransformer implements TransformIntf {
	
	private static HashMap<String,HashMap<String,Integer>> tallyMap = new HashMap<String,HashMap<String,Integer>>();

	private HashMap<String,HashMap<String,Integer>> _newTallyMap = null;

	public EmailTallyTransformer() {
		_newTallyMap = new HashMap<String,HashMap<String,Integer>>();
	}
	@Override
	public void convert(List<InputDataVO> inputList) throws TransformException {
		for(InputDataVO i : inputList) {
			transform(i);
		}
	}
	
	public void transform(InputDataVO i) throws TransformException {
		try {
			HashMap<String,Integer> availableTallyData = tallyMap.get(i.getId());
			if(availableTallyData == null) {
				availableTallyData = new HashMap<String,Integer>();
				tallyMap.put(i.getId(), availableTallyData);
			}
			List<LogVO> logs = i.getLogs();
			for (LogVO l : logs) {
				String email = l.getEmail();
				if (availableTallyData.get(email) != null) {
					int c = availableTallyData.get(email);
					c++;
					availableTallyData.put(email, c);
				} else {
					availableTallyData.put(email, 1);
				}
			}
			_newTallyMap.put(i.getId(), availableTallyData);
		} catch (Exception e) {
			throw new TransformException(" probably not able to convert to json", e);
		}
	}
	
	@Override
	public List<OutputDataVO> getTransformedData() {
		List<OutputDataVO> output = new ArrayList<OutputDataVO>();
		OutputDataVO od = null;
		if(_newTallyMap == null || _newTallyMap.isEmpty()) {
			return null;
		}
		for (String logId : _newTallyMap.keySet()) {
			od = new OutputDataVO();
			od.setLogs_id(logId);
			HashMap<String,Integer> tm = _newTallyMap.get(logId);
			List<TallyVO> tallyList = new ArrayList<TallyVO>();
			for(String email : tm.keySet()) {
				TallyVO t = new TallyVO();
				t.setEmail(email);
				t.setTotal(tm.get(email));
				tallyList.add(t);
			}
			od.setTally(tallyList);
			output.add(od);
		}
		return output;
		
	}

}
