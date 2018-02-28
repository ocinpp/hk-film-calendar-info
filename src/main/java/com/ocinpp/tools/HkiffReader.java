package com.ocinpp.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.ocinpp.tools.model.Event;

@ConfigurationProperties(prefix="app")
@Validated
@Component
public class HkiffReader {

	private static final String URL = "http://www.hkiff.org.hk/film/getdetail?fid=";
	
	@Min(2017)
	@Max(2046)
	private Integer year;
	
	public HkiffReader() {
	}
	
	public HkiffReader(Integer year) {
		this.year = year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public List<Event> getEvents(List<String> ids) throws IOException {
		List<Event> list = new ArrayList<Event>();
		for (int i = 0; i < ids.size(); i++) {
			list.addAll(getEvent(ids.get(i)));
		}
		return list;
	}
	
	public List<Event> getEvents(String... id) throws IOException {
		List<Event> list = new ArrayList<Event>();
		for (int i = 0; i < id.length; i++) {
			list.addAll(getEvent(id[i]));
		}
		return list;
	}

	public List<Event> getEvent(String id) throws IOException {
		List<Event> list = new ArrayList<Event>();

		// switch language to Chinese
		Document doc = Jsoup.connect(URL + id + "&lang=tc").get();		

		// get the number of "shows"
		Elements codeElement = doc.select("div.rightPart.span4 > div > ul> li");		
		
		for (int i = 1; i <= codeElement.size(); i++) {
			getEvent(doc, i).ifPresent(e -> {
				e.setUrl(URL + id);
				list.add(e);
			});
		}

		return list;
	}
	
	private Optional<Event> getEvent(Document doc, int index) {
		// read first element
		Event event = new Event(year);

		Elements titleElement = doc.select("section.dark_banner div.content > div.title");
		if (!titleElement.isEmpty()) {
			event.setTitle(titleElement.get(0).text().trim());
		}

		Elements introElement = doc.select("div.leftPart.span8 > div.intro > p");
		if (!introElement.isEmpty()) {
			event.setIntro(introElement.get(0).text().trim());
		}

		Elements codeElement = doc.select(String.format("div.rightPart.span4 > div > ul > li:nth-child(%d) > h1", index));
		if (!codeElement.isEmpty()) {
			event.setCode(codeElement.get(0).text().trim());
		}

		Elements dateElement = doc.select(String.format("div.rightPart.span4 > div > div > a:nth-child(%d) > span", index));
		Elements timeElement = doc.select(String.format("div.rightPart.span4 > div > ul > li:nth-child(%d) > h2", index));
		if (!dateElement.isEmpty()) {
			if (!timeElement.isEmpty()) {
				event.setDateAndTime(dateElement.get(0).text().trim(), timeElement.get(0).text().trim());
			} else {
				event.setDate(dateElement.get(0).text().trim());
			}
		}
		
		Elements venueElement = doc.select(String.format("div.rightPart.span4 > div > ul > li:nth-child(%d) > h3 > p:nth-child(1)", index));
		if (!venueElement.isEmpty()) {
			event.setVenue(venueElement.get(0).text().trim());
		}
		
		Elements contentElement = doc.select("div.intro > h2 > p");
		if (!contentElement.isEmpty()) {
			event.setContent(contentElement.get(0).text().trim());
		}		
		
		if (codeElement.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(event);
		} 
	}

}
