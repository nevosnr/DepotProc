package herts.davidneve.depotprocessor.model;

public class Customer {

    private String customerFirstName;
    private String customerLastName;
    public String parcelID;
    public int sequenceNumber;

    public Customer(String name, String id, int position){
        splitName(name);
        this.parcelID = id;
        this.sequenceNumber = position;
    }
    //method to break the customer name into its constituant parts
    private void splitName(String name){
        String[] fullName = name.split("\\s+");
        this.customerFirstName = fullName[0];
        //conditional handles event that customer does not provide a full name.
        if (fullName.length >1){
            this.customerLastName = fullName[1];
        }
    }

    public String getFirstName(){
        return customerFirstName;
    }

    public String getLastName(){
        return customerLastName;
    }

    public String getId(){
        return parcelID;
    } 

    public int getPos(){
        return sequenceNumber;
    }
    
}
