package com.ocinpp.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ocinpp.tools.model.Event;

/**
 * Class for generating a CSV file using the events retrieved from HkiffReader
 */
@ConfigurationProperties(prefix="app")
@Component
public class HkiffGoogleCalendarCsvGenerator implements CommandLineRunner {

	private final String FILE_CHARSET = "UTF-8";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String outputDirectory;
	
	private List<String> movieIds;
	
	private HkiffReader hkiffReader;
	
	@Autowired
	public HkiffGoogleCalendarCsvGenerator(HkiffReader hkiffReader) {
		this.hkiffReader = hkiffReader;
	}	
	
	public void setMovieIds(List<String> movieIds) {
		this.movieIds = movieIds;
	}
	
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
	
	public void run(String... args) throws Exception {
		logger.info("HkiffGoogleCalendarCsvGenerator is starting");		
		
		List<String> distinctMovieIds = movieIds.stream()
			     .distinct()
			     .collect(Collectors.toList());
		
		List<Event> events = hkiffReader.getEvents(distinctMovieIds);
		
		File outputDir = new File(outputDirectory);
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}
		
		// generate CSV for google calendar
		// https://support.google.com/calendar/answer/37118?hl=zh-Hant&visit_id=1-636251097472861427-1975917353&rd=2
		File outputFile = new File(outputDir, "events.csv");		
		try (BufferedWriter writer = Files.newBufferedWriter(outputFile.toPath(), Charset.forName(FILE_CHARSET))) {
			
			writer.write(String.format("\"%s\",%s,%s,%s,%s,\"%s\",\"%s\"", 
					"Subject", "Start Date", "Start Time", "End Date", "End Time",
					"Description", "Location"));
			writer.newLine();
			
			DateTimeFormatter df1 = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH); 
			DateTimeFormatter df2 = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
			
			events.forEach(event -> {
				try {
					writer.write(String.format("\"%s (%s)\",%s,%s,%s,%s,\"%s%n%s%n%nURL: %s\",\"%s\"", 
							event.getTitle(), 
							((event.getCode() != null && event.getCode().replaceAll("　", "").trim().length() > 0) ? event.getCode() : "免費放映"),
							event.getStartDateTime().format(df1), 
							event.getStartDateTime().format(df2), 
							event.getEndDateTime().format(df1), 
							event.getEndDateTime().format(df2),
							event.getIntro(),
							event.getContent(),
							event.getUrl(),
							event.getVenue()));
					writer.newLine();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			});
		}
	}
}
