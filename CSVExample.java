
/**
 * Write a description of CSVExample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVExample {
    
    public void countryinfo(CSVParser parser, String country){
        //returns string of info about the country or returns "not Found
        

        for(CSVRecord record : parser){
            String thecountry = record.get("Country");
            
            if(thecountry.contains(country)){
                String exports = record.get("Exports");
                String value = record.get("Value");
                System.out.println(country + " : " + exports + " : " + value);
            }
        }
    }
    
    public void tester() {
        FileResource fr= new FileResource();
        CSVParser parser = fr.getCSVParser();
    
        // be sure to reset parser after each method using
        parser =fr.getCSVParser();
        countryinfo(parser,"Germany");
    
    
    }

}
