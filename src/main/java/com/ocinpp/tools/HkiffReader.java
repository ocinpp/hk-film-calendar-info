package com.ocinpp.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.ocinpp.tools.model.Event;

@Component
public class HkiffReader {

	private static final String URL = "http://www.hkiff.org.hk/film/detail?id=";
	
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

		getFirstEvent(doc).ifPresent(e -> {
			e.setUrl(URL + id);
			list.add(e);
		});
		getSecondEvent(doc).ifPresent(e -> {
			e.setUrl(URL + id);
			list.add(e);
		});

		return list;
	}

	private Optional<Event> getFirstEvent(Document doc) {
		// read first element
		Event event = new Event();

		Elements titleElement = doc.select("section.dark_banner div.content > div.title");
		if (!titleElement.isEmpty()) {
			event.setTitle(titleElement.get(0).text().trim());
		}

		Elements introElement = doc.select("div.leftPart.span8 > div.intro > p");
		if (!introElement.isEmpty()) {
			event.setIntro(introElement.get(0).text().trim());
		}

		Elements codeElement = doc.select("div.rightPart.span4 > div > ul > li:nth-child(1) > h1");
		if (!codeElement.isEmpty()) {
			event.setCode(codeElement.get(0).text().trim());
		}

		Elements dateElement = doc.select("div.rightPart.span4 > div > div > a.active > span");
		Elements timeElement = doc.select("div.rightPart.span4 > div > ul > li:nth-child(1) > h2");
		if (!dateElement.isEmpty()) {
			if (!timeElement.isEmpty()) {
				event.setDateAndTime(dateElement.get(0).text().trim(), timeElement.get(0).text().trim());
			} else {
				event.setDate(dateElement.get(0).text().trim());
			}
		}
		
		Elements venueElement = doc.select("div.rightPart.span4 > div > ul > li:nth-child(1) > h3 > p:nth-child(1)");
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

	private Optional<Event> getSecondEvent(Document doc) {
		// read second element
		Event event = new Event();

		Elements titleElement = doc.select("section.dark_banner div.content > div.title");
		if (!titleElement.isEmpty()) {
			event.setTitle(titleElement.get(0).text().trim());
		}

		Elements introElement = doc.select("div.leftPart.span8 > div.intro > p");
		if (!introElement.isEmpty()) {
			event.setIntro(introElement.get(0).text().trim());
		}

		Elements codeElement = doc.select("div.rightPart.span4 > div > ul > li:nth-child(2) > h1");
		if (!codeElement.isEmpty()) {
			event.setCode(codeElement.get(0).text().trim());
		}

		Elements dateElement = doc.select("div.rightPart.span4 > div > div > a:nth-child(2) > span");
		Elements timeElement = doc.select("div.rightPart.span4 > div > ul > li:nth-child(2) > h2");
		if (!dateElement.isEmpty()) {
			if (!timeElement.isEmpty()) {
				event.setDateAndTime(dateElement.get(0).text().trim(), timeElement.get(0).text().trim());
			} else {
				event.setDate(dateElement.get(0).text().trim());
			}
		}

		Elements venueElement = doc.select("div.rightPart.span4 > div > ul > li:nth-child(2) > h3 > p:nth-child(1)");
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
