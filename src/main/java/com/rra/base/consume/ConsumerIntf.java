package com.rra.base.consume;

import java.util.List;

import com.rra.base.exception.ConsumeException;
import com.rra.base.modal.InputDataVO;

public interface ConsumerIntf {
	List<InputDataVO> read() throws ConsumeException;
	InputDataVO read(String data) throws ConsumeException;
}
