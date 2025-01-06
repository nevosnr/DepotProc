package herts.davidneve.depotprocessor.controller;

import herts.davidneve.depotprocessor.view.ParcelViewer;

public class DepotManager {

    private QOfCustomers _customerMgr;
    private ParcelViewer _mainDisp;
    private ParcelManager _parcelMgr;

    public DepotManager(){
        this._customerMgr = new QOfCustomers();
        this._mainDisp = new ParcelViewer();
        this._parcelMgr = new ParcelManager();
    }

    public void startApp(){

    }

    // public static void main(String[] args) {
    //     DepotManager manager = new DepotManager();
    //     manager.startApp();
    // }
}  

