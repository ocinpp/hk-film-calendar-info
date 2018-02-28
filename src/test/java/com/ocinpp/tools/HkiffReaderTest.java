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
		HkiffReader h = new HkiffReader(2018);
		List<Event> res = h.getEvent("322");
		
		Assert.assertEquals(1, res.size());
		
		int i = 0;
		
		Assert.assertEquals("Title", "嫲煩家族3走佬阿嫂", res.get(i).getTitle());
		Assert.assertEquals("Intro", "日本 / 2018 / 115 分鐘 / DCP / 彩色 / 中文字幕", res.get(i).getIntro());
		Assert.assertEquals("Code", "05KG4E2", res.get(i).getCode());
		Assert.assertEquals("Date", "2018-04-05", res.get(i).getDate().toString());
		Assert.assertEquals("Duration", 115, res.get(i).getDuration().intValue());
		Assert.assertEquals("Venue", "香港文化中心", res.get(i).getVenue());
	}

	@Test
	public void testGetEvent2() throws MalformedURLException, IOException {
		HkiffReader h = new HkiffReader(2018);
		List<Event> res = h.getEvent("241");
		
		Assert.assertEquals(2, res.size());
		
		int i = 0;
		
		Assert.assertEquals("Title", "河畔的惡意", res.get(i).getTitle());
		Assert.assertEquals("Intro", "日本 / 2018 / 118 分鐘 / DCP / 彩色 / 中文字幕", res.get(i).getIntro());
		Assert.assertEquals("Code", "27GC3P1", res.get(i).getCode());
		Assert.assertEquals("Date", "2018-03-27", res.get(i).getDate().toString());
		Assert.assertEquals("Duration", 118, res.get(i).getDuration().intValue());
		Assert.assertEquals("Venue", "The Grand Cinema", res.get(i).getVenue());
					
		i++;
		
		Assert.assertEquals("Title", "河畔的惡意", res.get(i).getTitle());
		Assert.assertEquals("Intro", "日本 / 2018 / 118 分鐘 / DCP / 彩色 / 中文字幕", res.get(i).getIntro());
		Assert.assertEquals("Code", "29KG3E2", res.get(i).getCode());
		Assert.assertEquals("Date", "2018-03-29", res.get(i).getDate().toString());
		Assert.assertEquals("Duration", 118, res.get(i).getDuration().intValue());
		Assert.assertEquals("Venue", "香港文化中心", res.get(i).getVenue());
	}
	
	@Test
	public void testGetEvent3() throws MalformedURLException, IOException {
		HkiffReader h = new HkiffReader(2018);
		List<Event> res = h.getEvent("232");
		
		Assert.assertEquals(3, res.size());
		
		int i = 0;
		
		Assert.assertEquals("Title", "持家小木蘭", res.get(i).getTitle());
		Assert.assertEquals("Intro", "愛爾蘭 / 加拿大 / 盧森堡 / 2017 / 93 分鐘 / DCP / 中文字幕", res.get(i).getIntro());
		Assert.assertEquals("Code", "25PU3M3", res.get(i).getCode());
		Assert.assertEquals("Date", "2018-03-25", res.get(i).getDate().toString());
		Assert.assertEquals("Duration", 93, res.get(i).getDuration().intValue());
		Assert.assertEquals("Venue", "香港理工大學賽馬會綜藝館", res.get(i).getVenue());
					
		i++;
		
		Assert.assertEquals("Title", "持家小木蘭", res.get(i).getTitle());
		Assert.assertEquals("Intro", "愛爾蘭 / 加拿大 / 盧森堡 / 2017 / 93 分鐘 / DCP / 中文字幕", res.get(i).getIntro());
		Assert.assertEquals("Code", "　", res.get(i).getCode());
		Assert.assertEquals("Date", "2018-04-02", res.get(i).getDate().toString());
		Assert.assertEquals("Duration", 93, res.get(i).getDuration().intValue());
		Assert.assertEquals("Venue", "香港大會堂", res.get(i).getVenue());
		
		i++;
		
		Assert.assertEquals("Title", "持家小木蘭", res.get(i).getTitle());
		Assert.assertEquals("Intro", "愛爾蘭 / 加拿大 / 盧森堡 / 2017 / 93 分鐘 / DCP / 中文字幕", res.get(i).getIntro());
		Assert.assertEquals("Code", "05MP4E1", res.get(i).getCode());
		Assert.assertEquals("Date", "2018-04-05", res.get(i).getDate().toString());
		Assert.assertEquals("Duration", 93, res.get(i).getDuration().intValue());
		Assert.assertEquals("Venue", "星影匯", res.get(i).getVenue());
	}
}
