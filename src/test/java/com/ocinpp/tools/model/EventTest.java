package com.ocinpp.tools.model;

import org.junit.Assert;
import org.junit.Test;

public class EventTest {

	@Test
	public void testGetDuration() {
		Event e = new Event();
		e.setIntro("Japan / 2017 / 123 分鐘 / DCP");
		Assert.assertEquals(123, e.getDuration().intValue());
		
		e = new Event();
		e.setIntro("Japan / 2017 / 12 分鐘 / DCP");
		Assert.assertEquals(12, e.getDuration().intValue());
		
		e = new Event();
		e.setIntro("Japan / 2017 / 1 分鐘 / DCP");
		Assert.assertEquals(1, e.getDuration().intValue());
		
		e = new Event();
		e.setIntro("Japan / 2017 / / DCP");
		Assert.assertNull(e.getDuration());
		
		e = new Event();
		e.setIntro("Japan / 2017 / DCP");
		Assert.assertNull(e.getDuration());
	}

}
