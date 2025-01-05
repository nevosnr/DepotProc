package herts.davidneve.depotprocessor.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomerReader {

    //Using JAVA internal libraries rather than introducing external dependancies 

    public Map<String, Customer> readCustomers(String PATH) {
        Map<String, Customer> customerMap = new HashMap<>();

        //Using any File IO operation it is good practice to use try/except/finally statements to ensure the application does not crash in the event of 
        //a file not being readable or available.

        try(BufferedReader br = new BufferedReader(new FileReader(PATH))){
            String line;
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                if (values.length >= 2) {
                    String fullName = values[0];
                    String parcelId = values[1];
                    int pos = 0;

                    Customer customer = new Customer(fullName, parcelId, pos);

                    customerMap.put(parcelId, customer);
                }
            }
        }
        catch (IOException error){
            System.out.println("There was an error in reading the Customer data.");
            error.printStackTrace();
        }
        return customerMap;
    }    
}
