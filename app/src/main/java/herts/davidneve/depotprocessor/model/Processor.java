package herts.davidneve.depotprocessor.model;

import java.util.Map;
import java.util.TreeMap;

public class Processor {

    public TreeMap<String, CombinedDataStore> combine(Map<String, Customer> customerMap, 
    Map<String, Parcel> parcelMap){
        TreeMap<String, CombinedDataStore> combineMap = new TreeMap<>();

        for (Customer customer : customerMap.values()) {
            Parcel parcel = parcelMap.get(customer.getId());
            if (parcel != null){
                CombinedDataStore combinedDataStore = new CombinedDataStore(
                    customer.getId(),
                    parcel.getWeight(),
                    parcel.getLength(),
                    parcel.getWidth(),
                    parcel.getHeight(),
                    parcel.getDaysinDepot(),
                    customer.getFullName()
                );
                combineMap.put(customer.getId(), combinedDataStore);
            }
        }
        return combineMap;
    }    
}
