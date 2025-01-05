package herts.davidneve.depotprocessor.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import herts.davidneve.depotprocessor.model.Parcel;

public class ParcelReader {

    public Map<String, Parcel> readParcels(String PATH){
        Map<String, Parcel> parcelMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >=6) {
                    String parcelId = values[0];
                    float weight = Float.parseFloat(values[1]);
                    float length = Float.parseFloat(values[2]);
                    float width = Float.parseFloat(values[3]);
                    float height = Float.parseFloat(values[4]);
                    int daysInDepot = Integer.parseInt(values[5]);

                    Parcel parcel = new Parcel(parcelId, weight, length, width, height, daysInDepot);

                    parcelMap.put(parcelId, parcel);
                }
            }
        }
        catch (IOException parcelError){
            System.out.println("There was an error in reading the Parcel data.");
            parcelError.printStackTrace();
        }   
    return parcelMap;
    }    
}
