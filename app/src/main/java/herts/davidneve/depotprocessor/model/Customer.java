package herts.davidneve.depotprocessor.model;

public class Customer {

    private String _customerFirstName;
    private String _customerLastName;
    private String _parcelID;
    private int _sequenceNumber;

    public Customer(String name, String id, int position){
        splitName(name);
        this._parcelID = id;
        this._sequenceNumber = position;
    }
    //method to break the customer name into its constituent parts
    private void splitName(String name){
        String[] fullName = name.split("\\s+");
        this._customerFirstName = fullName[0];
        //conditional handles event that customer does not provide a full name.
        if (fullName.length >1){
            this._customerLastName = fullName[1];
        }
    }
    //'Getters'
    public String getFirstName(){
        return _customerFirstName;
    }

    public String getLastName(){
        return _customerLastName;
    }

    public String getFullName(){
        return _customerFirstName + " " + _customerLastName;
    }

    public String getId(){
        return _parcelID;
    } 

    public int getPos(){
        return _sequenceNumber;
    }    
}
