package herts.davidneve.depotprocessor.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import herts.davidneve.depotprocessor.model.Customer;

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
                    int pos = 0;

                    Customer customer = new Customer(fullName, parcelId, pos);

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
        _customerMap.put(customer.getId(), customer);
    }

    public Customer getCustomer(String id){
        return _customerMap.get(id);
    }

    public Map<String, Customer> getCustomerMap(){
        return _customerMap;
    }
}
