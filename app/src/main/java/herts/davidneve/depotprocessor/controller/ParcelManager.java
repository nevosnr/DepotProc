package herts.davidneve.depotprocessor.controller;

import java.util.HashMap;
import java.util.Map;

import herts.davidneve.depotprocessor.model.Parcel;

public class ParcelManager {

    private Map<String, Parcel> parcelMap = new HashMap<>();

    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getId(), parcel);
    }

    public Parcel getParcel(String id){
        return parcelMap.get(id);
    }    
}
