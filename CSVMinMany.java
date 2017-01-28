/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMinMany {
	public CSVRecord smallestHourInFile(CSVParser parser) {
		//start with smallestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
		}
		//The smallestSoFar is the answer
		return smallestSoFar;
	}

	public void testColdestInDay () {
		FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
		CSVRecord smallest = smallestHourInFile(fr.getCSVParser());
		System.out.println("smallest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("TimeEST"));
	}

	public CSVRecord coldestInManyDays() {
		CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		String theFile = "";
		
		// iterate over files
		for (File f : dr.selectedFiles()) {
		    
			FileResource fr = new FileResource(f);
			// use method to get smallest in file.
			
			
			CSVRecord currentRow = smallestHourInFile(fr.getCSVParser());
			// use method to compare two records
			smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
			if (smallestSoFar == currentRow) {
			    theFile = f.getName();
			    FileResource theFileResource = fr;
			 }
		}
		//The smallestSoFar is the answer
		System.out.println("File with coldest temp is " + theFile);
		
		return smallestSoFar;
	}

	public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
		//If smallestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
			//Check if currentRow’s temperature < smallestSoFar’s
			if (currentTemp < smallestTemp) {
				//If so update smallestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}

public CSVRecord lowestHumidityInFile(CSVParser parser){
    //start with smallestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			//If smallestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
			double smallestHumidity = Double.parseDouble(smallestSoFar.get("Humidity"));
			//Check if currentRow’s temperature < smallestSoFar’s
			if (currentHumidity < smallestHumidity) {
				//If so update smallestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		}
		//The smallestSoFar is the answer
		return smallestSoFar;
}

public void testLowestHumidityInFile(){
FileResource fr = new FileResource();
CSVParser parser = fr.getCSVParser();
CSVRecord csv = lowestHumidityInFile(parser);
System.out.println("Lowest Humidity is " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
}


public CSVRecord lowestHumidityInManyFiles() {
    	CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		String theFile = "";
		
		// iterate over files
		for (File f : dr.selectedFiles()) {
		    
			FileResource fr = new FileResource(f);
			// use method to get smallest in file.
			
			
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			// use method to compare two records
			if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
			double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
			//Check if currentRow’s temperature < smallestSoFar’s
			if (currentTemp < smallestTemp) {
				//If so update smallestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
			if (smallestSoFar == currentRow) {
			    theFile = f.getName();
			    FileResource theFileResource = fr;
			 }
		}
		//The smallestSoFar is the answer
		System.out.println("File with lowest humidity is " + theFile);
		
		return smallestSoFar;
}

	
public void testLowestHumidityInManyFiles() {
  	CSVRecord smallest = lowestHumidityInManyFiles();
		System.out.println("lowest humidity was " + smallest.get("Humidity") +
				   " at " + smallest.get("DateUTC"));  
    
}



	public void testSmallestInManyDays () {
		CSVRecord smallest = coldestInManyDays();
		System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("DateUTC"));
	}
}
