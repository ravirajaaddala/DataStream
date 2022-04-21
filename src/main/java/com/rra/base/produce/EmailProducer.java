package com.rra.base.produce;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rra.base.exception.ProduceException;
import com.rra.base.modal.OutputDataVO;

//to send the out put in an email
@Component("email")
public class EmailProducer implements ProducerIntf{

	@Override
	public void write(List<OutputDataVO> data) throws ProduceException{
		// TODO Auto-generated method stub
		throw new ProduceException("email functionality not implemented"); // Not implementing
	}

}
