package com.ocinpp.tools.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to hold the event information such as title, date, start date time, duration, .. 
 **/
public class Event {

	private String title;
	private String intro;
	private String content;
	private String code;
	private LocalDate date;
	private ZonedDateTime startDateTime;
	private String venue;
	private Integer duration;
	private String url;
	
	// stores the year of the date
	private Integer year;
	
	public Event(Integer year) {
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
		//Pattern p = Pattern.compile(".*/\\s(\\d+)\\sminutes.*");		
		Pattern p = Pattern.compile(".*/\\s(\\d+)\\s分鐘.*");
		Matcher m = p.matcher(intro);
		if (m.matches()) {
			duration = Integer.parseInt(m.group(1));
		}
	}
	public Integer getDuration() {
		return duration;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(String date) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yyyy");
		this.date = LocalDate.parse(date + "/" + this.year, df);
	}
	public void setDateAndTime(String date, String time) {
		this.setDate(date);		
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a", Locale.ENGLISH);
		df2 = df2.withZone(ZoneId.of("Asia/Hong_Kong"));
		this.startDateTime = ZonedDateTime.parse(date + "/" + this.year + " " + time + "", df2);
	}
	public ZonedDateTime getStartDateTime() {
		return startDateTime;
	}
	public ZonedDateTime getEndDateTime() {
		return (this.getDuration() > 0) ? (startDateTime.plusMinutes(this.getDuration())) : startDateTime;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Title: " + this.getTitle() + "\n");
		sb.append("Intro: " + this.getIntro() + "\n");
		sb.append("Content: " + this.getContent() + "\n");
		sb.append("Code: " + this.getCode() + "\n");
		sb.append("Date: " + this.getDate() + "\n");
		sb.append("StartDateTime: " + this.getStartDateTime() + "\n");
		sb.append("EndDateTime: " + this.getEndDateTime() + "\n");
		sb.append("Duration: " + this.getDuration() + "\n");
		sb.append("Venue: " + this.getVenue()+ "\n");
		sb.append("Url: " + this.getUrl()+ "\n");
		return sb.toString();
	}
	

}
