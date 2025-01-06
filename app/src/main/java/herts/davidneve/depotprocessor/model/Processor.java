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
                float costIncurred = calculateCost(parcel.getDaysinDepot(), parcel.getWeight(), parcel.getLength(), parcel.getHeight(), parcel.getWidth());
                CombinedDataStore combinedDataStore = new CombinedDataStore(
                    customer.getId(),
                    parcel.getWeight(),
                    parcel.getLength(),
                    parcel.getWidth(),
                    parcel.getHeight(),
                    parcel.getDaysinDepot(),
                    customer.getFullName(),
                    costIncurred
                );
                combineMap.put(customer.getId(), combinedDataStore);
            }
        }
        return combineMap;
    }
    
    public float calculateCost(int daysStored, float weight, float length, float height, float width){
        float costPerDay  = 1.50f;
        float factorWeight = 0.10f;
        float factorDims = 0.05f;

        float dimsCost = (length + height + width) * factorDims;
        float weightCost = weight * factorWeight;
        float storeCost = daysStored * costPerDay;

        float costIncurred = dimsCost + weightCost + storeCost;

        return Math.round(costIncurred * 100.00)/100.00f;
    }
}
