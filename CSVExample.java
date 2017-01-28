
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
        
        Boolean countryfound = false;
        
        for(CSVRecord record : parser){
            String thecountry = record.get("Country");
            if(thecountry.contains(country)){
                String exports = record.get("Exports");
                String value = record.get("Value");
                System.out.println(country + " : " + exports + " : " + value);
                countryfound = true;
            }
            
        }
        if (countryfound==false){
            System.out.println(country + " NOT FOUND ");
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        
        System.out.println();
        System.out.println("These countries export BOTH " + exportItem1 + " and " + exportItem2);
        
        for(CSVRecord record : parser){
            String thecountry = record.get("Country");
           Boolean isthereItem1 = record.get("Exports").toLowerCase().contains(exportItem1.toLowerCase());
           Boolean isthereItem2 = record.get("Exports").toLowerCase().contains(exportItem2.toLowerCase());
            
            if(isthereItem1 && isthereItem2){
                System.out.println(record.get("Country"));
            }
            
        }
    }
    
    public Integer numberOfExporters(CSVParser parser, String exportItem){
        Integer country_count =0;
        
        for(CSVRecord record : parser){
           
           Boolean isthere_exportItem = record.get("Exports").toLowerCase().contains(exportItem.toLowerCase());
           if (isthere_exportItem) {
              country_count= country_count+1; 
            }
            
         
        }
        
          System.out.println();
          return country_count; 
    }
    
    
    public Long convertDollarStringToInteger(String inputstring){
        String stripped = inputstring.replace("$","");
        stripped = stripped.replace(",","");
	stripped = stripped.replace(" ","");
		//System.out.println(stripped);
	
		long result = Long.parseLong(stripped);

		return result;
    }
    
    public void bigExporters(CSVParser parser, String minValueStr){
        // convert minValue string to numeric
        
        long minValueInt = convertDollarStringToInteger(minValueStr);
		//System.out.println("Min Value int is " + minValueInt);
        for(CSVRecord record : parser){
			String theVal = record.get("Value");
				//System.out.println("theVal string is " + theVal);
			
            long loopValue = convertDollarStringToInteger(theVal);
			//System.out.println("loopValue is " + loopValue);
            if (loopValue>= minValueInt){
                System.out.println(record.get("Country") + "  " + record.get("Value"));  
            }
        
        }
        
    } //end bigExporters
    
    
    
    public void tester() {
        FileResource fr= new FileResource();
        CSVParser parser = fr.getCSVParser();
    
        // be sure to reset parser after each method using
        parser =fr.getCSVParser();
        
        // part 2
        countryinfo(parser,"Germany");
        parser =fr.getCSVParser();
        
        //part 3
        listExportersTwoProducts(parser,"cotton", "flowers");
        parser =fr.getCSVParser();
                
        //part 4
        String exportItem="cocoa";
        Integer count=numberOfExporters(parser, exportItem);
        System.out.println("There are " + count + " countries that export " + exportItem);
        System.out.println();
        parser =fr.getCSVParser();
                
        //part 5
        String minValue = "$999,999,999,999";
        System.out.println("These countries export more than " + minValue + " dollars");
        bigExporters(parser, minValue);
        parser = fr.getCSVParser();
        
    }

}
