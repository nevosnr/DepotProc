// /*
//  * This source file was generated by the Gradle 'init' task
//  */
// package herts.davidneve.depotprocessor;

// import java.util.Map;

// import herts.davidneve.depotprocessor.model.Customer;
// import herts.davidneve.depotprocessor.model.Parcel;
// import herts.davidneve.depotprocessor.utils.CustomerReader;
// import herts.davidneve.depotprocessor.utils.ParcelReader;

// public class App {

//     public static void main(String[] args) {

//         System.out.println("This is my application");
        
//         ParcelReader readerUtil = new ParcelReader();
//         Map<String, Parcel> parcelMap = readerUtil.readParcels("app/src/main/resources/Parcels.csv");

//         for (Map.Entry<String, Parcel> entry : parcelMap.entrySet()){
//             Parcel parcel = entry.getValue();
//             System.out.println("Parcel ID: " + parcel.getId() + ", Days in Depot: " + parcel.getDaysinDepot());
//         }

//         CustomerReader custReadUtil = new CustomerReader();
//         Map<String, Customer> customerMap = custReadUtil.readCustomers("app/src/main/resources/Custs.csv");

//         for(Map.Entry<String, Customer> entry : customerMap.entrySet()){
//             Customer custoemr = entry.getValue();
//             System.out.println("Customer: " + custoemr.getFirstName() +" "+ custoemr.getLastName() + ", Parcel ID: " + custoemr.getId() );
//         }
//     }
// }
