/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
	public CSVRecord coldestHourInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			//If largestSoFar is nothing
			if (smallestSoFar == null) {
				smallestSoFar = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
				//Check if currentRow’s temperature > largestSoFar’s
				if (currentTemp < smallestTemp) {
					//If so update largestSoFar to currentRow
					smallestSoFar = currentRow;
			}
		}
}
		//The smallestSoFar is the answer
		return smallestSoFar;
	}


	public void testColdestInDay () {
		FileResource fr = new FileResource();
		CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
		System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("TimeEST"));
	}

}