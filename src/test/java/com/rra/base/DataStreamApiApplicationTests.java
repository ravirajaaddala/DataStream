package com.rra.base;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rra.base.engine.DataStreamer;
import com.rra.base.enums.UserOptionsEnum;
import com.rra.base.exception.ConsumeException;
import com.rra.base.exception.ProduceException;
import com.rra.base.exception.TransformException;

@SpringBootTest
class DataStreamApiApplicationTests {

	@Autowired
	DataStreamer da ;
	
	@Test
	void contextLoads() {
		da.setOption(UserOptionsEnum.FILE.getName());
		System.out.println("executing test case process");
		try {
			da.process();
			System.out.println("test case process executed successfully");
		} catch (ConsumeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProduceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
