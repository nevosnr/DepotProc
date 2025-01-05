package herts.davidneve.depotprocessor.controller;

import herts.davidneve.depotprocessor.view.MainDisp;

public class DepotManager {

    private QOfCustomers _customerMgr;
    private MainDisp _display;
    private ParcelManager _parcelMgr;

    public DepotManager(){
        this._customerMgr = new QOfCustomers();
        this._display = new MainDisp();
        this._parcelMgr = new ParcelManager();
    }

    public void startApp(){

    }

    // public static void main(String[] args) {
    //     DepotManager manager = new DepotManager();
    //     manager.startApp();
    // }
}  

