package com.rra.base.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StreamStore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long streamStoreId;
	@Column(length = 2048)
	private String data;
	private Date enteredDate;
	public Long getStreamStoreId() {
		return streamStoreId;
	}
	public void setStreamStoreId(Long streamStoreId) {
		this.streamStoreId = streamStoreId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getEnteredDate() {
		return enteredDate;
	}
	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}
	
	
}
