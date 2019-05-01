[![Known Vulnerabilities](https://snyk.io/test/github/ocinpp/hk-film-calendar-info/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/ocinpp/hk-film-calendar-info?targetFile=pom.xml)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Focinpp%2Fhk-film-calendar-info.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Focinpp%2Fhk-film-calendar-info?ref=badge_shield)

# hk-film-calendar-info
Generate CSV files from film info page to be imported to Calendar. 
Chinese information is retrieved by this application

## Build

Use Maven to build the package using the command
	
	mvn package

## Usage

Input the movie IDs found in the movie website and add in **application.properties**.

	app.movieIds=121,60,5,157
	
Define the output directory.

	app.outputDirectory=D:\\result
	
Run HkiffApplication.

	java -jar target/hkiff-1.0.0.jar
	
You can specify the location of the application.properties file explicitly or put it in the current directory.

	java -jar target/hkiff-1.0.0.jar --spring.config.location=application.properties 	
	
Finally, you get a CSV file (events.csv) in the output directory for importing into Google Calendar	

# Disclaimer
The application has no affiliation with HKIFF.