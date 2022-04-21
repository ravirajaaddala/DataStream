package com.rra.base.modal;

import java.util.List;

public class InputDataVO {

	private String id = null;
	private List<LogVO> logs = null;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<LogVO> getLogs() {
		return logs;
	}
	public void setLogs(List<LogVO> logs) {
		this.logs = logs;
	}
	
	
}
