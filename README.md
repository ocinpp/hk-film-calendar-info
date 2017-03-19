# hk-film-calendar-info
Generate CSV files from film info page to be imported to Calendar

## Build

Use Maven to build the package using the command
	
	mvn package

## Usage

Input the movie IDs found in the movie website and add in application.properties.

	app.movieIds=121,60,5,157
	
Define the output directory.

	app.outputDirectory=D:\\result
	
Run HkiffApplication.

	java -jar target/hkiff-1.0.0.jar
	
Finally, you get a CSV file (events.csv) in the output directory for importing into Google Calendar	

# Disclaimer
The application has no affiliation with HKIFF.