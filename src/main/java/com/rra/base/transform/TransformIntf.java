package com.rra.base.transform;

import java.util.List;

import com.rra.base.exception.TransformException;
import com.rra.base.modal.InputDataVO;
import com.rra.base.modal.OutputDataVO;

public interface TransformIntf {

	void convert(List<InputDataVO> inputList) throws TransformException;
	List<OutputDataVO> getTransformedData() ;
}
