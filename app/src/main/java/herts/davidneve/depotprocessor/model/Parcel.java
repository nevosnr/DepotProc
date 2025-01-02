package herts.davidneve.depotprocessor.model;

public class Parcel {

    //Definition of variables required for Parcels
    private String _parcelID;
    private float _weight, _length, _width, _height;
    private int _numDaysInDepot;
    public boolean isCollected;
    public boolean isDiscountable;

    //Constructor for the PArcel object.
    public Parcel(String id, float weight, float length, float width, float height, int daysInDepot){
        this._parcelID = id;
        this._weight = weight;
        this._length = length;
        this._width = width;
        this._height = height;
        this._numDaysInDepot = daysInDepot;
    }
    //'Getters' //Bloated compared to using properties in C#!
    public String getId(){
        return _parcelID;
    }

    public float getWeight(){
        return _weight;
    }

    public float getLength(){
        return _length;
    }

    public float getWidth(){
        return _width;
    }

    public float getHeight(){
        return _height;
    }    
}
