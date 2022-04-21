package com.rra.base.produce;

import java.util.List;

import com.rra.base.exception.ProduceException;
import com.rra.base.modal.OutputDataVO;

public interface ProducerIntf {

	void write(List<OutputDataVO> data) throws ProduceException;
}
