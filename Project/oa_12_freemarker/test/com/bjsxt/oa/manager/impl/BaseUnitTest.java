package com.bjsxt.oa.manager.impl;

import org.springframework.test.AbstractTransactionalSpringContextTests;

public class BaseUnitTest extends AbstractTransactionalSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"beans.xml"};
	}

}
