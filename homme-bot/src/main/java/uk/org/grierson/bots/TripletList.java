package uk.org.grierson.bots;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TripletList {
    private static final Logger LOGGER = LogManager.getLogger(TripletList.class);
    public static final TripletList TRIPLETS = new TripletList();
    
    private List<String> internalTripletList = new LinkedList<>();
    
    private TripletList() { 
        loadExistingTriplets();
    }
  
    private void loadExistingTriplets() {
        LOGGER.trace("Reading existing triplets");
        
        try ( Scanner scanner = new Scanner(new File("tripletFile.txt")) ) {
            while ( scanner.hasNextLine() ) {
                internalTripletList.add(scanner.nextLine());
            }
        }
        catch ( FileNotFoundException e ) {
            LOGGER.error("Unable to load existing triplets - starting with none", e);
        }   
        
        internalTripletList.add("how");
    }
    
    public boolean containsMatchingTriplet(String str) {
        if ( str == null || str.length() == 0 ) {
            return false;
        }
        
        Iterator<String> iter = internalTripletList.iterator();
        
        while ( iter.hasNext() ) { 
            if ( str.contains(iter.next()) ) {
                return true;
            }
        }
        
        return false;
    }
}
