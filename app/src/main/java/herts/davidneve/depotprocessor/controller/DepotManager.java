package herts.davidneve.depotprocessor.controller;

import javax.swing.SwingUtilities;

import herts.davidneve.depotprocessor.model.ParcelManager;
import herts.davidneve.depotprocessor.model.QOfCustomers;
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                _mainDisp.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        DepotManager manager = new DepotManager();
         manager.startApp();
    }
}  

