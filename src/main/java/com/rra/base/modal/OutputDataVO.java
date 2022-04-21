package com.rra.base.modal;

import java.util.List;

public class OutputDataVO {

	private String logs_id = null;
	private List<TallyVO> tally = null;
	public String getLogs_id() {
		return logs_id;
	}
	public void setLogs_id(String logs_id) {
		this.logs_id = logs_id;
	}
	public List<TallyVO> getTally() {
		return tally;
	}
	public void setTally(List<TallyVO> tally) {
		this.tally = tally;
	}
	
	
	
}
