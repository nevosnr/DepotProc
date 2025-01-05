package herts.davidneve.depotprocessor.utils;

import java.util.Map;
import java.util.TreeMap;

import herts.davidneve.depotprocessor.model.Customer;
import herts.davidneve.depotprocessor.model.Parcel;

public class Processor {

    public TreeMap<String, String> combine(Map<String, Customer> customerMap, 
    Map<String, Parcel> parcelMap){
        TreeMap<String, String> combineMap = new TreeMap<>();

        for (Customer customer : customerMap.values()) {
            Parcel parcel = parcelMap.get(customer.getId());
            if (parcel != null){
                combineMap.put(customer.getId(), customer.getLastName());
            }
        }
        return combineMap;
    }    
}
