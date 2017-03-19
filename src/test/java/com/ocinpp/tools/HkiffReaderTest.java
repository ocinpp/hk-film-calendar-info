package com.ocinpp.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ocinpp.tools.model.Event;



public class HkiffReaderTest {

	@Test
	public void testGetEvent() throws MalformedURLException, IOException {
		HkiffReader h = new HkiffReader();
		List<Event> res = h.getEvent("45");
		
		Assert.assertEquals(2, res.size());
		Assert.assertEquals("Title", "報告NASA，大大大大大鑊了﹗", res.get(0).getTitle());
		Assert.assertEquals("Intro", "斯洛文尼亞, 克羅地亞 / 2016 / 88 分鐘 / DCP / 彩色 / 中文字幕", res.get(0).getIntro());
		Assert.assertEquals("Code", "　", res.get(0).getCode());
		Assert.assertEquals("Date", "2017-04-15", res.get(0).getDate().toString());
		Assert.assertEquals("Duration", 88, res.get(0).getDuration().intValue());
		Assert.assertEquals("Venue", "香港大會堂", res.get(0).getVenue());
				
		Assert.assertEquals("Title", "報告NASA，大大大大大鑊了﹗", res.get(1).getTitle());
		Assert.assertEquals("Intro", "斯洛文尼亞, 克羅地亞 / 2016 / 88 分鐘 / DCP / 彩色 / 中文字幕", res.get(1).getIntro());
		Assert.assertEquals("Code", "24MP4P1", res.get(1).getCode());
		Assert.assertEquals("Date", "2017-04-24", res.get(1).getDate().toString());
		Assert.assertEquals("Duration", 88, res.get(1).getDuration().intValue());
		Assert.assertEquals("Venue", "星影匯", res.get(1).getVenue());
	}

}
