package herts.davidneve.depotprocessor.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QOfCustomers {

    private Map<String, Customer> _customerMap = new HashMap<>();

    public void readCustomers(String PATH) {

        try(BufferedReader br = new BufferedReader(new FileReader(PATH))){
            String line;
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                if (values.length >= 2) {
                    String fullName = values[0];
                    String parcelId = values[1];
                    Customer customer = new Customer(fullName, parcelId);

                    _customerMap.put(parcelId, customer);
                }
            }
        }
        catch (IOException error){
            System.out.println("There was an error in reading the Customer data.");
            error.printStackTrace();
        }
    }  
    
    public void addCustomer(Customer customer){
        if (isCustValid(customer)){
        _customerMap.put(customer.getId(), customer);
        } else{
            System.out.println("Something has gone wrong with your customer, try something else!");
        }
    }

    public Customer getCustomer(String id){
        return _customerMap.get(id);
    }

    public Map<String, Customer> getCustomerMap(){
        return _customerMap;
    }

    //to ensure data integrity of the application a simple check will be performed prior to any data being added.
    private boolean isCustValid(Customer customer) {
        return customer.getFirstName() != null && customer.getId() != null;
    }

}
